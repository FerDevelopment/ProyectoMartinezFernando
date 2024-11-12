package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyectomartinezfernando.modelo.Tarjeta

@Composable
fun ResumenPago(modifier : Modifier , onVolverFormularioPago : () -> Unit , onPagar : () -> Unit) {
    val tarjeta = Tarjeta()
    Spacer(modifier = modifier.height(16.dp))
    Column {
        FuncionesComunes().CardTarjeta(modifier , tarjeta)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxSize() ,
            horizontalArrangement = Arrangement.SpaceAround ,
            verticalAlignment = Alignment.Bottom
           ) {
            Botones(onVolverFormularioPago , onPagar)
        }
    }

}

// Función para enmascarar el número de la tarjeta
fun enmascararNumeroTarjeta(numero : String) : String {
    return if (numero.length >= 16) {
        "**** **** **** " + numero.takeLast(4)
    }
    else {
        "****"
    }
}

// Función para enmascarar el CVC
fun enmascararCVC() : String {
    return "***"
}

@Composable
private fun Botones(onVolverFormularioPago : () -> Unit , onPagar : () -> Unit) {
    Button(onClick = onVolverFormularioPago) {
        Text(stringResource(R.string.cambiar_datos_tarjeta))
    }

    Button(onClick = onPagar) {
        Text(stringResource(R.string.pagar))
    }

}