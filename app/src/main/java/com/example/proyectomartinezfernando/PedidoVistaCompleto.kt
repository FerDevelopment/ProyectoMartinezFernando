package com.example.proyectomartinezfernando

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.proyectomartinezfernando.data.Pedido

private val textTmTarjResum = 4.em

@Composable
fun VerPedidoCompleto(pedido: Pedido, modifier: Modifier = Modifier) {
   modifier.padding(10.dp)
   Column(modifier = modifier.fillMaxWidth()) {
      Row {
         Image(
            painter = painterResource(pedido.vehiculo.imagen),
            contentDescription = stringResource(R.string.tipo_de_vehiculo),
            contentScale = ContentScale.Fit,
            modifier = Modifier.height(300.dp)
         )
      }
      Row {
         Text(
            stringResource(R.string.dias_alquiler, pedido.dias),
            fontSize = textTmTarjResum,
            fontWeight = FontWeight.Bold
         )
      }
      Row {
         Text(
            stringResource(R.string.total_pedido, pedido.totalPagar),
            fontSize = textTmTarjResum,
            fontWeight = FontWeight.Bold
         )
      }
      FuncionesComunes().SacarInfoVehiculo(pedido.vehiculo)
      Text(
         text = stringResource(R.string.usuario),
         fontWeight = FontWeight.Bold,
         fontSize = textTmTarjResum
      )
      Column(
         modifier = Modifier
            .padding(8.dp)
      ) {
         FuncionesComunes().SacarInfoUser(pedido.user, 4.em)
      }
   }

}