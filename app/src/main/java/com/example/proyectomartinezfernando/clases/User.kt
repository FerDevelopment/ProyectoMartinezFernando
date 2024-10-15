package com.example.proyectomartinezfernando.clases

import androidx.annotation.DrawableRes
import com.example.proyectomartinezfernando.R

class User() {
    val nombreUser : String = "Predeterminado"
    val correo : String = "Predeterminado@Predeterminado.com"
    val telefono : String = "123 45 67 89"
    val nombre : String = "Predeterminado"
    val apellido : String = "PredeterminadS"

    @DrawableRes
    val fotoPerfil : Int = R.drawable.default_perfil

    constructor(
            nombreUser : String ,
            correo : String ,
            telefono : String ,
            nombre : String ,
            apellido : String ,
            @DrawableRes fotoPerfil : Int
               ) : this()
}
