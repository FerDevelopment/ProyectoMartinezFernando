package com.example.proyectomartinezfernando.data

import com.example.proyectomartinezfernando.modelo.Vehiculo

data class Pedido(
        val vehiculo : Vehiculo = Vehiculo() ,
        val user : User = User() ,
        val dias : Int = 0 ,
        val totalPagar : Int = 0 ,
        val tarjeta : Tarjeta = Tarjeta() ,
        val gps : Boolean = false
                 )
