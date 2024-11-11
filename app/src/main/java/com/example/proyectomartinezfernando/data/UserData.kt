package com.example.proyectomartinezfernando.data

import com.example.proyectomartinezfernando.modelo.Pedido

data class UserData(
        val nombreUser : String = "Predeterminado" ,
        val correo : String = "Predeterminado@Predeterminado.com" ,
        val telefono : String = "123 45 67 89" ,
        val nombre : String = "Predeterminado" ,
        val apellido : String = "PredeterminadS" ,
        val pedidos : MutableList<Pedido> = mutableListOf()
                   )
