package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.example.proyectomartinezfernando.datos.CargarDatos

@Composable
fun ListaPedidos(modifier : Modifier = Modifier , onVolverInicio : () -> Unit) {
    Column(modifier = modifier) {
        Text("Pedidos" , fontSize = 7.em)
        PedidosOut()
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


