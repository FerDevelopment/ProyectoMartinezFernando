package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.em
import com.example.proyectomartinezfernando.datos.CargarDatos

@Composable
fun ListaPedidos(modifier : Modifier = Modifier , onVolverInicio : () -> Unit) {
    Column(modifier = modifier) {
        Text("Pedidos" , fontSize = 7.em)
        PedidosOut()
        FloatingActionButton(
            onClick = onVolverInicio
                            ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack , stringResource(R.string.bonton_volver_atras))
        }
    }
}

@Composable
fun PedidosOut() {
    LazyColumn {
        items(CargarDatos().cargarLista()) { pedido ->
            FuncionesComunes().ImprimirCardPedido(pedido)
        }
    }
}


