package com.example.proyectomartinezfernando.clases

import androidx.annotation.DrawableRes

open class Vehiculo() {
    val precioDia : Int = 0
    val gps : Boolean = false
    val type : String = ""

    @DrawableRes
    val imagen : Int = 0

    constructor(
            precioDia : Int , gps : Boolean , @DrawableRes imagen : Int
               ) : this()

}
