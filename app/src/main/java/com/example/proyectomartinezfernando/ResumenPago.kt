package com.example.proyectomartinezfernando

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectomartinezfernando.data.Tarjeta

@Composable
fun ResumenPago(
        modifier : Modifier ,
        onVolverFormularioPago : () -> Unit ,
        onPagar : () -> Unit ,
        tarjeta : Tarjeta
               ) {
    Column(
        modifier = modifier
                .padding(15.dp)
                .fillMaxHeight() ,
        verticalArrangement = Arrangement.SpaceBetween
          ) {
        FuncionesComunes().CardTarjeta(modifier , tarjeta)

        Row(
            modifier = Modifier.fillMaxSize() ,
            horizontalArrangement = Arrangement.SpaceAround ,
            verticalAlignment = Alignment.Bottom
           ) {
            Botones(onVolverFormularioPago , onPagar)
        }
    }

}

@Composable
private fun Botones(onVolverFormularioPago : () -> Unit , onPagar : () -> Unit) {
    Button(onClick = onVolverFormularioPago) {
        Text(stringResource(R.string.cambiar_datos_tarjeta))
    }

    Button(modifier = Modifier.width(125.dp) , onClick = onPagar) {
        Text(stringResource(R.string.pagar))
    }

}