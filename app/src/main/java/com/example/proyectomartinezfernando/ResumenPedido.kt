package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectomartinezfernando.data.Pedido

@Composable
fun ResumenPedido(
   modifier: Modifier,
   onVolverCrearPedido: () -> Unit,
   onIrFomularioPago: () -> Unit,
   pedido: Pedido,
) {
   Column(
      modifier
         .padding(15.dp)
         .fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
   ) {
      VerPedidoCompleto(pedido, modifier = modifier)
      Botones(onVolverCrearPedido, onIrFomularioPago)
   }

}

@Composable
private fun Botones(onCrearPedido: () -> Unit, onIrFomularioPago: () -> Unit) {
   Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceAround
   ) {
      Button(
         onClick = onCrearPedido
      ) {
         Text(stringResource(R.string.cambiar_datos))
      }


      Button(modifier = Modifier.width(125.dp), onClick = onIrFomularioPago) {
         Text(stringResource(R.string.pagar))
      }
   }
}