package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyectomartinezfernando.data.Tarjeta

@Composable
fun FormularioPago(modifier: Modifier, navController: NavHostController) {
   val tiposTarjeta = listOf("VISA", "MasterCard", "Euro 6000")
   var tipoTarjeta by remember { mutableStateOf("") }
   var numeroTarjeta by remember { mutableStateOf("") }
   var cvc by remember { mutableStateOf("") }
   var caducidad by remember { mutableStateOf("") }

   Column(
      modifier = modifier
         .padding(20.dp)
         .fillMaxWidth()
         .fillMaxHeight(),
   ) {
      Text(
         stringResource(R.string.formulario_de_pago),
         fontSize = 4.em,
         fontWeight = FontWeight.Bold
      )
      // Tipo de tarjeta
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
         onValueChange = { numeroTarjeta = it },
         label = { Text(stringResource(R.string.numero_de_tarjeta)) },
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
         modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
      )

      Row(
         modifier = modifier,
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         // Caducidad
         TextField(
            value = caducidad,
            onValueChange = { caducidad = it },
            label = { Text(stringResource(R.string.caducidad)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
               .padding(10.dp)
         )
         // CVC
         TextField(
            value = cvc,
            onValueChange = { cvc = it },
            label = { Text(stringResource(R.string.cvc)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
               .padding(10.dp)
         )
      }
      Botones(navController)
   }
   val tarjeta = Tarjeta(tipoTarjeta, numeroTarjeta, cvc, caducidad)

}

@Composable
private fun Botones(navController: NavController) {
   Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.Bottom
   ) {
      Button(onClick =
      {
         navController.navigate("resumen_pedido")
      }
      ) {
         Text(stringResource(R.string.resumen_de_pedido))
      }

      Button(onClick =
      {
         navController.navigate("resumen_pago") {
         }
      }) {
         Text(stringResource(R.string.resumen_de_pago))
      }
   }
}