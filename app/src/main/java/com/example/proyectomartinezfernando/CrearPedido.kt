package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.proyectomartinezfernando.clases.Coche
import com.example.proyectomartinezfernando.clases.Moto
import com.example.proyectomartinezfernando.clases.Patin
import com.example.proyectomartinezfernando.clases.Pedido
import com.example.proyectomartinezfernando.clases.User
import com.example.proyectomartinezfernando.clases.Vehiculo

@Composable
fun CrearPedido(modifier: Modifier, navController: NavHostController, user: User = User()) {
   user.pedidos.add(Pedido(user))
   Column(
      modifier = modifier
         .verticalScroll(rememberScrollState())
         .padding(20.dp)
         .fillMaxHeight(), horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.SpaceEvenly
   ) {
      Text(
         stringResource(R.string.realizar_pedido),
         fontSize = 4.em,
         fontWeight = FontWeight.Bold,
      )
      FormularioPedido(user, modifier)
      Spacer(modifier = modifier.height(10.dp))
      Botones(navController)
   }

}

@Composable
private fun FormularioPedido(
   user: User = User(),
   modifier: Modifier
) {
   user.pedidos.last().vehiculo = elegirVehiculo(modifier.fillMaxWidth())
   HorizontalDivider()
   user.pedidos.last().dias = campoDias(modifier)
   HorizontalDivider()
   Row {
      Text("Total de pedido: ${user.pedidos.last().totalPagar}")
   }

}

@Composable
private fun elegirVehiculo(modifier: Modifier): Vehiculo {
   val listVehiculo = listOf(
      R.string.coche,
      R.string.moto,
      R.string.patinete
   )
   var gps = false
   var vehiAux = Vehiculo()
   val (vehiculoElegido, onVehiculoElegido) = remember { mutableIntStateOf(listVehiculo[0]) }
   var vehiculoElegido1 = vehiculoElegido
   Column {
      Row {
         Column {
            listVehiculo.forEach { opcion ->
               Row(
                  modifier = Modifier.selectable(
                     selected = (opcion == vehiculoElegido),
                     onClick = {
                        vehiculoElegido1 = opcion
                        onVehiculoElegido(opcion)
                     }
                  ),
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  RadioButton(selected = (opcion == vehiculoElegido1),
                     onClick = {
                        vehiculoElegido1 = opcion
                        onVehiculoElegido(opcion)
                     })
                  Text(text = stringResource(opcion))
               }
            }
         }
         Column(verticalArrangement = Arrangement.Top) {
            gps = gps()
         }
      }
      Row {
         Column {
            HorizontalDivider()
            vehiAux = when (listVehiculo.indexOf(vehiculoElegido)) {
               0 -> formularioCoche()
               1 -> formularioMoto()
               2 -> formularioPatinete()
               else -> {
                  Vehiculo()
               }
            }
         }
      }
   }

   vehiAux.gps = gps
   return vehiAux

}

@Composable
fun formularioPatinete(): Vehiculo {
   val type = "unic"
   HorizontalDivider()
   return Patin(type)
}

@Composable
fun formularioMoto(): Vehiculo {
   val type: String = tipoMoto()
   HorizontalDivider()

   return Moto(type)
}

@Composable
fun tipoMoto(): String {
   val tiposMotos = listOf(R.string._250, R.string._125, R.string._50)
   val (tipoMoto, onEleccion) = remember { mutableIntStateOf(tiposMotos[0]) }

   Column {
      Text(
         stringResource(R.string.potencia_de_la_moto),
         fontWeight = FontWeight.Bold,
         fontSize = 3.em
      )
      tiposMotos.forEach { tipo ->
         Row(
            modifier = Modifier.selectable(
               selected = (tipo == tipoMoto),
               onClick = {
                  onEleccion(tipo)
               }
            ),
            verticalAlignment = Alignment.CenterVertically
         ) {
            RadioButton(
               selected = (tipo == tipoMoto),
               onClick = {
                  onEleccion(tipo)
               } // Se maneja el clic en el Row
            )
            Text(text = stringResource(tipo) + " cc")
         }
      }
   }
   return when (tipoMoto) {
      0 -> "250"
      1 -> "125"
      2 -> "50"
      else -> "0"
   }

}

@Composable
fun formularioCoche(): Vehiculo {
   val type: String = tipoCoche()
   HorizontalDivider()
   return Coche(type)
}

@Composable
fun tipoCoche(): String {
   val tiposCoche = listOf(R.string.diesel, R.string.gasolina, R.string.electrico)
   val (tipoCoche, onEleccion) = remember { mutableIntStateOf(tiposCoche[0]) }

   Column {
      Text(stringResource(R.string.tipo_de_coche), fontWeight = FontWeight.Bold, fontSize = 3.em)
      tiposCoche.forEach { tipo ->
         Row(
            modifier = Modifier.selectable(
               selected = (tipo == tipoCoche),
               onClick = {
                  onEleccion(tipo)
               }
            ),
            verticalAlignment = Alignment.CenterVertically
         ) {
            RadioButton(
               selected = (tipo == tipoCoche),
               onClick = {
                  onEleccion(tipo)
               } // Se maneja el clic en el Row
            )
            Text(text = stringResource(tipo))
         }
      }
   }
   return when (tipoCoche) {
      0 -> "die"
      1 -> "gas"
      2 -> "ele"
      else -> "null"
   }

}

@Composable
fun gps(): Boolean {
   var gps by remember { mutableStateOf(true) }
   // Opción 1: True
   Row(modifier = Modifier.selectable(selected = gps, onClick = {
      gps = true
   }), verticalAlignment = Alignment.CenterVertically) {
      RadioButton(
         selected = gps,
         onClick = { gps = true }
      )
      Text(stringResource(R.string.con_gps))
   }
   Row(modifier = Modifier.selectable(selected = gps, onClick = {
      gps = false
   }), verticalAlignment = Alignment.CenterVertically) {
      RadioButton(
         selected = !gps,
         onClick = { gps = false }
      )
      Text(stringResource(R.string.sin_gps))
   }

   return gps
}

@Composable
fun campoDias(modifier: Modifier): Int {
   var dias by remember { mutableStateOf("") }
   TextField(
      value = dias,
      onValueChange = {
         dias = it
      },
      singleLine = true,
      label = { Text(text = stringResource(R.string.dias)) },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      modifier = modifier.padding(0.dp)
   )
   return dias.toIntOrNull() ?: 0
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
         navController.navigate("pantalla_inicial")
      }
      ) {
         Text(stringResource(R.string.volver_al_inicio))
      }

      Button(onClick =
      {
         navController.navigate("resumen_pedido") {
         }
      }) {
         Text(stringResource(R.string.resumen_pedido))
      }
   }
}