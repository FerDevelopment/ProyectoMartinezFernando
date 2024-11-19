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
import androidx.compose.ui.unit.dp
import com.example.proyectomartinezfernando.data.User

@Composable
fun PantallaInicial(
        modifier : Modifier = Modifier ,
        user : User = User() ,
        onAbrirListaPedido : () -> Unit ,
        onCrearPedido : () -> Unit
                   ) {
    Column(
        modifier = modifier
                .padding(15.dp)
                .fillMaxHeight() ,
        verticalArrangement = Arrangement.SpaceBetween
          ) {
        TarjetaUser(user)
        Botones(onAbrirListaPedido , onCrearPedido)
    }
}

@Composable
fun TarjetaUser(user : User) {
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
            Row {
                Column(horizontalAlignment = Alignment.Start) {
                    FuncionesComunes().SacarInfoUser(user)
                }
            }
        }
    }

}

@Composable
private fun Botones(botonAtras : () -> Unit , botonAdelante : () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.SpaceAround
       ) {
        /*Boton 1 -->  Listar Pedidos*/
        Button(
            onClick = botonAtras
              ) {
            Text(stringResource(R.string.lista_pedidos))
        }
        /*Boton2 --> Realizar Pedido*/

        Button(onClick = botonAdelante) {
            Text(stringResource(R.string.hacer_pedido))
        }
    }
}