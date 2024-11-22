package com.example.proyectomartinezfernando.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyectomartinezfernando.data.Pedido
import com.example.proyectomartinezfernando.data.Tarjeta
import com.example.proyectomartinezfernando.datos.CargarDatos
import com.example.proyectomartinezfernando.modelo.Coche
import com.example.proyectomartinezfernando.modelo.Moto
import com.example.proyectomartinezfernando.modelo.Patin
import com.example.proyectomartinezfernando.modelo.Vehiculo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PedidoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Pedido())
    val uiState : StateFlow<Pedido> = _uiState.asStateFlow()
    private val _totalPedido = _uiState.value.totalPagar
    var totalPedido by mutableIntStateOf(_totalPedido)
        private set

    fun actualizarVehiculo(vehiculoActual : Vehiculo) {
        _uiState.update { estado ->
            estado.copy(
                vehiculo = vehiculoActual
                       )
        }
        actualizarTotal()
    }

    fun actualizarDias(dias : String) {
        val diasActual : Int = dias.toIntOrNull() ?: 0

        _uiState.update { estado ->
            estado.copy(
                dias = diasActual
                       )
        }
        actualizarTotal()

    }

    private fun actualizarTotal() {
        actualizarPrecioDia()
        val dias by mutableIntStateOf(_uiState.value.dias)
        val precioDia by mutableIntStateOf(_uiState.value.vehiculo.precioDia)
        totalPedido = dias * precioDia

        _uiState.update { estado ->
            estado.copy(
                totalPagar = totalPedido
                       )
        }

    }

    fun actualizarTarjeta(tarjetaActual : Tarjeta) {
        _uiState.update { estado ->
            estado.copy(
                tarjeta = tarjetaActual
                       )
        }
    }

    private fun actualizarPrecioDia() {
        when (_uiState.value.vehiculo) {
            is Coche -> actuPrecioCoche()
            is Moto -> actuPrecioMoto()
            is Patin -> actuPrecioPatin()
            else -> {}
        }
    }

    private fun actuPrecioPatin() {
        var precioDiaAux by mutableIntStateOf(0)
        val vehiculoAux = _uiState.value.vehiculo
        precioDiaAux = if (vehiculoAux.gps) {
            CargarDatos().GPSPRICE + CargarDatos().precioPatinete(vehiculoAux.type)
        }
        else {
            CargarDatos().precioPatinete(vehiculoAux.type)
        }

        vehiculoAux.precioDia = precioDiaAux

        _uiState.update { estado ->
            estado.copy(
                vehiculo = vehiculoAux
                       )
        }
    }

    private fun actuPrecioCoche() {
        var precioDiaAux by mutableIntStateOf(0)
        val vehiculoAux = _uiState.value.vehiculo
        precioDiaAux = if (vehiculoAux.gps) {
            CargarDatos().GPSPRICE + CargarDatos().precioCoche(vehiculoAux.type)
        }
        else {
            CargarDatos().precioCoche(vehiculoAux.type)
        }


        vehiculoAux.precioDia = precioDiaAux

        _uiState.update { estado ->
            estado.copy(
                vehiculo = vehiculoAux
                       )
        }
    }

    private fun actuPrecioMoto() {
        val vehiculoAux = _uiState.value.vehiculo
        val precioDiaAux : Int = if (vehiculoAux.gps) {
            CargarDatos().GPSPRICE + CargarDatos().precioMoto(vehiculoAux.type)
        }
        else {
            CargarDatos().precioMoto(vehiculoAux.type)
        }

        vehiculoAux.precioDia = precioDiaAux

        _uiState.update { estado ->
            estado.copy(
                vehiculo = vehiculoAux
                       )
        }
    }

    fun actualizarGPS(gpsSelect : Boolean) {
        val vehiAux = _uiState.value.vehiculo
        vehiAux.gps = gpsSelect

        _uiState.update { estado ->
            estado.copy(
                gps = gpsSelect , vehiculo = vehiAux
                       )
        }
        actualizarTotal()
    }
}
