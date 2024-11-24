package com.example.proyectomartinezfernando.data

import androidx.annotation.DrawableRes
import com.example.proyectomartinezfernando.R

data class User(
   val nombreUser : String = "Predeterminado",
   var correo : String = "Predeterminado@Predeterminado.com",
   val telefono : String = "123 45 67 89",
   val nombre : String = "Predeterminado",
   val apellido : String = "PredeterminadS",
   val tarjera : Tarjeta = Tarjeta(),
   val pedidos : MutableList<Pedido> = arrayListOf(),
   @DrawableRes
        val fotoPerfil : Int = R.drawable.default_perfil,
   val info : List<String> = listOf(
            nombre ,
            apellido ,
            telefono ,
            nombreUser ,
            correo
                                        )
               )
