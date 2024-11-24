package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.proyectomartinezfernando.data.Tarjeta

@Composable
fun FormularioPago(
   modifier: Modifier,
   onVolverResumenPedido: () -> Unit,
   onIrResumenPago: () -> Unit,
   onAgregarTarjeta: (Tarjeta) -> Unit,
   tarjeta: Tarjeta
) {
   val tiposTarjeta = listOf("VISA", "MasterCard", "Euro 6000")
   var tipoTarjeta by remember { mutableStateOf(tarjeta.tipo) }
   var numeroTarjeta by remember { mutableStateOf(tarjeta.numero) }
   var cvc by remember { mutableStateOf("") }
   var caducidad by remember { mutableStateOf(tarjeta.caducidad) }
   val cardNumberRegex = Regex("^[0-9]{13,19}$")
   val expirationDateRegex = Regex("^(0[1-9]|1[0-2])/(\\d{2})$")
   val cvvRegex = Regex("^\\d{3,4}$")
   var numErr by remember { mutableStateOf(false) }
   var expErr by remember { mutableStateOf(false) }
   var cvvErr by remember { mutableStateOf(false) }
   Column(
      modifier = modifier
         .padding(20.dp)
         .fillMaxSize(),
   ) {
      Text(
         text = stringResource(R.string.tipo_de_tarjeta),
         fontWeight = FontWeight.Bold,
         fontSize = 3.em
      )
      Column {
         tiposTarjeta.forEach { tipo ->
            Row(
               modifier = Modifier
                  .fillMaxWidth()
                  .selectable(selected = (tipo == tipoTarjeta), onClick = { tipoTarjeta = tipo }),
               verticalAlignment = Alignment.CenterVertically
            ) {
               RadioButton(
                  selected = (tipo == tipoTarjeta),
                  onClick = { tipoTarjeta = tipo }
               )
               Text(text = tipo)
            }
         }
      }
      // Número de tarjeta
      TextField(
         value = numeroTarjeta,
         onValueChange = {
            numeroTarjeta = it
            numErr = !cardNumberRegex.matches(numeroTarjeta)
         },
         label = { Text(stringResource(R.string.numero_de_tarjeta)) },
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
         isError = numErr,
         modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
      )

      Row(
         modifier = modifier.fillMaxWidth(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween,
      ) {
         // Caducidad
         TextField(
            value = caducidad,
            placeholder = { Text(stringResource(R.string.mm_aaaa)) },
            onValueChange = {
               caducidad = it
               if (it.length == 2) {
                  caducidad += "/"
               }
               expErr = !expirationDateRegex.matches(caducidad)
            },
            isError = expErr,
            label = { Text(stringResource(R.string.caducidad)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
               .padding(10.dp)
               .weight(1f)
         )
         // CVC
         TextField(
            value = cvc,
            onValueChange = {
               cvc = it
               cvvErr = !cvvRegex.matches(cvc)
            },
            label = { Text(stringResource(R.string.cvc)) },
            isError = cvvErr,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier
               .padding(10.dp)
               .weight(1f)
         )
      }

      Botones(onVolverResumenPedido, onIrResumenPago)
   }
   onAgregarTarjeta(Tarjeta(tipoTarjeta, numeroTarjeta, cvc, caducidad))

}

@Composable
private fun Botones(onVolverResumenPedido: () -> Unit, onIrResumenPago: () -> Unit) {
   Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.Bottom,
      horizontalArrangement = Arrangement.SpaceAround
   ) {
      Button(
         onClick = onVolverResumenPedido
      ) {
         Text(stringResource(R.string.cambiar_datos))
      }

      Button(onClick = onIrResumenPago) {
         Text(stringResource(R.string.pagar))
      }
   }
}