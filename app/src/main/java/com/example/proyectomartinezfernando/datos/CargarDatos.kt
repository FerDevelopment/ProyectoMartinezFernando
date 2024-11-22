package com.example.proyectomartinezfernando.datos

import android.util.Log
import com.example.proyectomartinezfernando.data.Pedido

class CargarDatos {
    val GPSPRICE : Int = 5
    val COCHELISTATIPOS = listOf(
        "die" , "gas " , "ele "
                                )
    val MOTOSLISTATIPOS = listOf(
        "250" ,
        "125" ,
        "50" ,
                                )

    fun cargarLista() : List<Pedido> = listOf(
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
        Pedido() ,
                                             )

    fun precioCoche(type : String) : Int {
        Log.e("Tipo entrante", type)
        return when (type) {
            "die" -> 25
            "gas " -> 20
            "ele " -> 15
            else -> 0
        }
    }

    fun precioMoto(type : String) : Int {
        return when (type) {
            "250" -> 20
            "125" -> 15
            "50" -> 10
            else -> 0
        }
    }

    fun precioPatinete(type : String) : Int {
        return 5;
    }
}