package com.example.proyectomartinezfernando

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.proyectomartinezfernando.data.Pedido
import com.example.proyectomartinezfernando.data.Tarjeta
import com.example.proyectomartinezfernando.data.User
import com.example.proyectomartinezfernando.modelo.Vehiculo

private val textTmTarjResum = 2.8.em

class FuncionesComunes {
   @Composable
   fun SacarInfoUser(user: User, fontSizeText: TextUnit = 2.5.em) {
      val contenido = user.info
      val campos = listOf(
         R.string.nombre,
         R.string.apellidos,
         R.string.tel_fono,
         R.string.usuario,
         R.string.correo
      )
      SacarInfoGeneral(fontSizeText, campos, contenido)
   }

   @Composable
   private fun SacarInfoGeneral(
      fontSizeText: TextUnit = 3.em,
      @StringRes campos: List<Int>,
      contenido: List<String>
   ) {
      for (i in campos.indices) {
         Row {
            Text(
               text = stringResource(campos[i]) + ": ",
               fontSize = fontSizeText,
               fontWeight = FontWeight.Bold
            )
            Text(contenido[i], fontSize = fontSizeText)
         }
      }
   }

   @Composable
   fun ImprimirCardPedido(pedido: Pedido,
                          modifier: Modifier = Modifier,
                          onVerPedido: (Pedido) -> Unit
   ) {
      Spacer(modifier = modifier.height(16.dp))
      Card(onClick = {
         onVerPedido(pedido)
      }, modifier = Modifier.padding(10.dp)) {
         Row(modifier = Modifier.fillMaxWidth()) {
            Column(
               modifier = Modifier
                  .weight(1f)
                  .padding(8.dp)
            ) {
               Row {
                  Image(
                     painter = painterResource(pedido.vehiculo.imagen),
                     contentDescription = stringResource(R.string.tipo_de_vehiculo)
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
            }
            Column(
               modifier = Modifier
                  .weight(3f)
                  .padding(8.dp)
            ) {
               FuncionesComunes().SacarInfoUser(pedido.user)
            }
         }
      }
      Spacer(modifier = Modifier.height(16.dp))
   }

   @Composable
   fun SacarInfoVehiculo(vehiculo: Vehiculo) {
      val campos = listOf(
         R.string.gps,
         R.string.tipo,
         R.string.ppd
      )
      val contenido = vehiculo.info()
      val fontSize = 4.em
      this.SacarInfoGeneral(campos = campos, contenido = contenido, fontSizeText = fontSize)

   }

   @Composable
   fun CardTarjeta(
      modifier: Modifier = Modifier,
      tarjeta: Tarjeta
   ) {
      Card(modifier = modifier) {
         Row(modifier = Modifier.fillMaxWidth()) {
            Column(
               modifier = Modifier
                  .weight(1f)
                  .padding(8.dp)
            ) {
               // Mostrar el tipo de tarjeta
               Row {
                  Text(
                     text = stringResource(R.string.tipo_de_tarjeta) + ": " + tarjeta.tipo,
                     fontSize = 18.sp,
                     fontWeight = FontWeight.Bold
                  )
               }
               // Mostrar número de tarjeta enmascarado
               Row {
                  Text(
                     text = stringResource(R.string.numero_de_tarjeta) + ": " + enmascararNumeroTarjeta(
                        tarjeta.numero
                     ),
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Normal
                  )
               }
               // Mostrar CVC (también enmascarado por seguridad)
               Row {
                  Text(
                     text = stringResource(R.string.cvc) + ": " + enmascararCVC(),
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Normal
                  )
               }
               // Mostrar fecha de caducidad
               Row {
                  Text(
                     text = stringResource(R.string.caducidad) + ": " + tarjeta.caducidad,
                     fontSize = 16.sp,
                     fontWeight = FontWeight.Normal
                  )
               }
            }
         }
      }
   }

   // Función para enmascarar el número de la tarjeta
   private fun enmascararNumeroTarjeta(numero: String): String {
      return if (numero.length >= 16) {
         "**** **** **** " + numero.takeLast(4)
      } else {
         "****"
      }
   }

   // Función para enmascarar el CVC
   private fun enmascararCVC(): String {
      return "***"
   }

}