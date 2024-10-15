package com.example.proyectomartinezfernando

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.proyectomartinezfernando.clases.User

@Composable
fun PantallaInicial(modifier : Modifier = Modifier) {
    Column(
        modifier = modifier
                .padding(15.dp)
                .fillMaxHeight() ,
        verticalArrangement = Arrangement.SpaceBetween
          ) {
        TarjetaUser()
        Botones()
    }
}

@Composable
fun TarjetaUser() {
    Card(
        modifier = Modifier
                .fillMaxWidth()
        ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth() ,
                horizontalArrangement = Arrangement.Center
               ) {
                Image(
                    painter = painterResource(user.fotoPerfil) ,
                    contentDescription = stringResource(R.string.foto_de_perfil) ,
                    modifier = Modifier.size(120.dp) ,
                    contentScale = ContentScale.Fit
                     )
            }
            Row() {
                Column(horizontalAlignment = Alignment.Start) {
                    Row {
                        Text(
                            text = "Nombre" + ": " ,
                            fontSize = 4.em ,
                            fontWeight = FontWeight.Bold
                            )
                        Text(user.nombre , fontSize = 4.em)
                    }
                    Row {
                        Text(
                            text = "Apellidos" + ": " ,
                            fontSize = 4.em ,
                            fontWeight = FontWeight.Bold
                            )
                        Text(user.apellido , fontSize = 4.em)
                    }

                    Row {
                        Text(
                            text = "Teléfono" + ": " ,
                            fontSize = 4.em ,
                            fontWeight = FontWeight.Bold
                            )
                        Text(user.telefono , fontSize = 4.em)
                    }

                    Row {
                        Text(
                            text = "Usuario" + ": " ,
                            fontSize = 4.em ,
                            fontWeight = FontWeight.Bold
                            )
                        Text(user.nombreUser , fontSize = 4.em)
                    }

                    Row {
                        Text(
                            text = "Correo" + ": " ,
                            fontSize = 4.em ,
                            fontWeight = FontWeight.Bold
                            )
                        Text(user.correo , fontSize = 4.em)
                    }
                }
            }
        }
    }

}

@Composable
fun Botones() {
    Row(
        modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.SpaceAround
       ) {
        /*Boton 1 -->  Listar Pedidos*/
        Button(onClick =
        {
        }
              ) {
            Text(stringResource(R.string.lista_pedidos))
        }
        /*Boton2 --> Realizar Pedido*/

        Button(onClick =
        {
        }) {
            Text(stringResource(R.string.hacer_pedido))
        }
    }
}