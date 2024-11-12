package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyectomartinezfernando.modelo.Pedido

@Composable
fun ResumenPedido(
        modifier : Modifier ,
        onCrearPedido : () -> Unit , onIrFomularioPago : () -> Unit ,
        pedido : Pedido = Pedido()
                 ) {
    Column(
        modifier
                .padding(15.dp)
                .fillMaxHeight() ,
        verticalArrangement = Arrangement.SpaceBetween
          ) {
        FuncionesComunes().ImprimirCardPedido(pedido)
        Botones(onCrearPedido , onIrFomularioPago)
    }

}

@Composable
private fun Botones(onCrearPedido : () -> Unit , onIrFomularioPago : () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.SpaceAround
       ) {
        Button(
            onClick = onCrearPedido
              ) {
            Text(stringResource(R.string.cambiar_datos))
        }


        Button(onClick = onIrFomularioPago) {
            Text(stringResource(R.string.pagar))
        }
    }
}