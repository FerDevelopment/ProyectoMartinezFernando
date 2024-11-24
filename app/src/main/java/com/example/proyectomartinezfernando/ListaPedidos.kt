package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.proyectomartinezfernando.data.Pedido

@Composable
fun ListaPedidos(modifier: Modifier = Modifier,
                 onVolverInicio: () -> Unit,
                 pedidos: List<Pedido>,
                 onVerPedido: (Pedido) -> Unit
) {
   Column(modifier = modifier) {
      PedidosOut(pedidos = pedidos, onVerPedido = onVerPedido)
      FloatingActionButton(
         onClick = onVolverInicio
      ) {
         Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.bonton_volver_atras))
      }
   }
}

@Composable
fun PedidosOut(pedidos: List<Pedido>, onVerPedido: (Pedido) -> Unit) {
   LazyColumn {
      items(pedidos) { pedido ->
         ResumenPedidoCard(
            pedido = pedido,
            onVerPedido = onVerPedido,
         )
      }
   }
}

@Composable
fun ResumenPedidoCard(
   pedido: Pedido, onVerPedido: (Pedido) -> Unit
) {
   FuncionesComunes().ImprimirCardPedido(pedido = pedido, onVerPedido = onVerPedido)

}


