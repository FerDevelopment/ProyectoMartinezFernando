package com.example.proyectomartinezfernando

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.em
import com.example.proyectomartinezfernando.clases.Pedido
import com.example.proyectomartinezfernando.datos.CargarDatos

@Composable
fun ListaPedidos(modifier : Modifier = Modifier) {
    Column {
        Text("Pedidos" , fontSize = 7.em)
        PedidosOut()
    }
}

@Composable
fun PedidosOut() {
    LazyColumn {
        items(CargarDatos().cargarLista()) { pedido ->
            ImprimirCardPedido(pedido)
        }
    }
}

@Composable
fun ImprimirCardPedido(pedido : Pedido) {
    Card {
        Row {
            Column {
                Image(
                    painter = painterResource(pedido.vehiculo.imagen) ,
                    contentDescription = stringResource(R.string.tipo_de_vehiculo)
                     )
            }
            Column {
            }
        }
    }
}
