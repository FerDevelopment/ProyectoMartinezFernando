package com.example.proyectomartinezfernando

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
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
import com.example.proyectomartinezfernando.datos.CargarDatos
import com.example.proyectomartinezfernando.modelo.Coche
import com.example.proyectomartinezfernando.modelo.Moto
import com.example.proyectomartinezfernando.modelo.Patin
import com.example.proyectomartinezfernando.modelo.Vehiculo

@Composable
fun CrearPedido(
        modifier : Modifier ,
        onVolverInicio : () -> Unit ,
        onIrResumenPedido : () -> Unit ,
        onElegirDiasAlquiler : (String) -> Unit ,
        onElegirVehiculo : (Vehiculo) -> Unit ,
        onElegirGPS : (Boolean) -> Unit ,
        precioFinal : Int ,
        gps : Boolean ,
        dias : String ,
        vehiculo : Vehiculo
               ) {
    Column(
        modifier = modifier
                .padding(20.dp)
                .fillMaxHeight() ,
        horizontalAlignment = Alignment.Start ,
        verticalArrangement = Arrangement.SpaceEvenly
          ) {
        Text(
            stringResource(R.string.realizar_pedido) ,
            fontSize = 4.em ,
            fontWeight = FontWeight.Bold ,
            )

        FormularioPedido(
            onElegirDiasAlquiler , onElegirVehiculo , precioFinal , modifier ,
            onElegirGPS , gps , dias , vehiculo
                        )



        Row(
            modifier = Modifier.fillMaxSize() ,
            verticalAlignment = Alignment.Bottom ,
            horizontalArrangement = Arrangement.SpaceAround
           ) {
            Botones(botonAtras = onVolverInicio , botonAdelante = onIrResumenPedido)
        }
    }

}

@Composable
private fun FormularioPedido(
        onElegirDiasAlquiler : (String) -> Unit ,
        onElegirVehiculo : (Vehiculo) -> Unit ,
        precioFinal : Int ,
        modifier : Modifier ,
        onElegirGPS : (Boolean) -> Unit ,
        gps : Boolean , dias : String ,
        vehiculo : Vehiculo
                            ) {
    ElegirVehiculo(
        onElegirVehiculo = onElegirVehiculo , gps = gps , onElegirGPS = onElegirGPS ,
        vehiculo = vehiculo
                  )
    HorizontalDivider()
    /*Campo dias completado*/
    CampoDias(modifier = modifier , onElegirDiasAlquiler , dias)

    HorizontalDivider()
    Row {
        Text(stringResource(R.string.total_de_pedido , precioFinal))
    }

}

@Composable
private fun ElegirVehiculo(
        onElegirVehiculo : (Vehiculo) -> Unit , onElegirGPS : (Boolean) -> Unit ,
        gps : Boolean , vehiculo : Vehiculo
                          ) {
    val listVehiculo = listOf(
        R.string.coche ,
        R.string.moto ,
        R.string.patinete
                             )
    val vehiculoActual = when (vehiculo) {
        is Coche -> listVehiculo[0]
        is Moto -> listVehiculo[1]
        is Patin -> listVehiculo[2]
        else -> listVehiculo[0]
    }
    var vehiAux by remember { mutableStateOf(Vehiculo()) }
    val obtenerVehiculo : (Vehiculo) -> Unit = { vehiAux = it }
    val (vehiculoElegido , onVehiculoElegido) = remember { mutableIntStateOf(vehiculoActual) }
    var vehiculoElegido1 = vehiculoElegido
    Column {
        Row {
            Column {
                listVehiculo.forEach { opcion ->
                    Row(
                        modifier = Modifier.selectable(
                            selected = (opcion == vehiculoElegido) ,
                            onClick = {
                                vehiculoElegido1 = opcion
                                onVehiculoElegido(opcion)
                            }
                                                      ) ,
                        verticalAlignment = Alignment.CenterVertically
                       ) {
                        RadioButton(selected = (opcion == vehiculoElegido1) ,
                            onClick = {
                                vehiculoElegido1 = opcion
                                onVehiculoElegido(opcion)
                            })
                        Text(text = stringResource(opcion))
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.SpaceAround ,
                horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                Gps(onElegirGPS = onElegirGPS , selected = gps)
            }
        }
        Row {
            Column {
                HorizontalDivider()
                when (listVehiculo.indexOf(vehiculoElegido1)) {
                    0 -> FormularioCoche(obtenerVehiculo , gps)
                    1 -> FormularioMoto(obtenerVehiculo , gps)
                    2 -> FormularioPatinete(obtenerVehiculo , gps)
                    else -> {
                        Vehiculo()
                    }
                }
            }
        }
    }

    FuncionesComunes().SacarInfoVehiculo(vehiAux)

    onElegirVehiculo(vehiAux)
}

@Composable
fun FormularioPatinete(obtenerVehiculo : (Vehiculo) -> Unit , gps : Boolean) {
    val type = "unic"
    HorizontalDivider()
    obtenerVehiculo(Patin(type = type , gps = gps))
}

@Composable
fun FormularioMoto(obtenerVehiculo : (Vehiculo) -> Unit , gps : Boolean) {
    val type : String = tipoMoto()
    HorizontalDivider()

    obtenerVehiculo(Moto(type = type , gps = gps))
}

@Composable
fun tipoMoto() : String {
    val tiposMotos = listOf(R.string._250 , R.string._125 , R.string._50)
    val (tipoMoto , onEleccion) = remember {
        mutableIntStateOf(
            tiposMotos[0]
                         )
    }

    Column {
        Text(
            stringResource(R.string.potencia_de_la_moto) ,
            fontWeight = FontWeight.Bold ,
            fontSize = 3.em
            )
        tiposMotos.forEach { tipo ->
            Row(
                modifier = Modifier.selectable(
                    selected = (tipo == tipoMoto) ,
                    onClick = {
                        onEleccion(tipo)
                    }
                                              ) ,
                verticalAlignment = Alignment.CenterVertically
               ) {
                RadioButton(
                    selected = (tipo == tipoMoto) ,
                    onClick = {
                        onEleccion(tipo)
                    } // Se maneja el clic en el Row
                           )
                Text(text = stringResource(tipo) + " cc")
            }
        }
    }
    return when (tiposMotos.indexOf(tipoMoto)) {
        0 -> CargarDatos().MOTOSLISTATIPOS[0]
        1 -> CargarDatos().MOTOSLISTATIPOS[1]
        2 -> CargarDatos().MOTOSLISTATIPOS[2]
        else -> "0"
    }

}

@Composable
fun FormularioCoche(obtenerVehiculo : (Vehiculo) -> Unit , gps : Boolean) {
    var type : String by remember { mutableStateOf("15") }
    TipoCoche { type = it }
    Log.e("Ni idea" , type)
    HorizontalDivider()

    obtenerVehiculo(Coche(type = type , gps = gps))
}

@Composable
fun TipoCoche(onCambiarTipo : (String) -> Unit) {
    val tiposCoche = listOf(R.string.diesel , R.string.gasolina , R.string.electrico)
    val (tipoCoche , onEleccion) = remember { mutableIntStateOf(tiposCoche[0]) }

    Column {
        Text(
            stringResource(R.string.tipo_de_coche) ,
            fontWeight = FontWeight.Bold ,
            fontSize = 3.em
            )
        tiposCoche.forEach { tipo ->
            Row(
                modifier = Modifier.selectable(
                    selected = (tipo == tipoCoche) ,
                    onClick = {
                        onEleccion(tipo)
                    }
                                              ) ,
                verticalAlignment = Alignment.CenterVertically
               ) {
                RadioButton(
                    selected = (tipo == tipoCoche) ,
                    onClick = {
                        onEleccion(tipo)
                    } // Se maneja el clic en el Row
                           )
                Text(text = stringResource(tipo))
            }
        }
    }
    Log.e("Tipo coche Set" , tipoCoche.toString())
    Log.e("Tipo coche Set Lista" , tiposCoche[0].toString())
    Log.e("IndexOF" , tiposCoche.indexOf(tipoCoche).toString())
    val type = when (tiposCoche.indexOf(tipoCoche)) {
        0 -> CargarDatos().COCHELISTATIPOS[0]
        1 -> CargarDatos().COCHELISTATIPOS[1]
        2 -> CargarDatos().COCHELISTATIPOS[2]
        else -> "null"
    }
    onCambiarTipo(type)

}

@Composable
fun Gps(onElegirGPS : (Boolean) -> Unit , selected : Boolean) {
    var gps by remember { mutableStateOf(selected) }
    // Opción 1: True
    Row(
        modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.SpaceAround ,
        verticalAlignment = Alignment.CenterVertically
       ) {
        Text(stringResource(R.string.gps))
        Switch(
            checked = gps ,
            onCheckedChange = { estado ->
                gps = estado
                onElegirGPS(estado)
            }
              )
    }

}

@Composable
fun CampoDias(modifier : Modifier , onElegirDiasAlquiler : (String) -> Unit , diasP : String) {
    var dias by remember { mutableStateOf(diasP) }
    TextField(
        value = dias ,
        onValueChange = {
            dias = it
            onElegirDiasAlquiler(dias)
        } ,
        singleLine = true ,
        label = { Text(text = stringResource(R.string.dias)) } ,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) ,
        modifier = modifier.padding(0.dp)
             )

}

@Composable
private fun Botones(botonAtras : () -> Unit , botonAdelante : () -> Unit) {
    Button(
        onClick =
        botonAtras
          ) {
        Text(stringResource(R.string.volver_al_inicio))
    }

    Button(
        onClick = botonAdelante
          )
    {
        Text(stringResource(R.string.resumen_pedido))
    }

}