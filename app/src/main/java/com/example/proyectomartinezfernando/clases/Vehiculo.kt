package com.example.proyectomartinezfernando.clases

import androidx.annotation.DrawableRes
import com.example.proyectomartinezfernando.R

open class Vehiculo() {
   var precioDia: Int = 0
   var gps: Boolean = false
   var type: String = ""

   @DrawableRes
   var imagen: Int = R.drawable.cocheprueba
   val info = listOf(
      if (gps) {
         "con gps"
      } else {
         "sin gps"
      },
      type,
      "$precioDia"
   )

   constructor(
      precioDia: Int, gps: Boolean, type: String, @DrawableRes imagen: Int
   ) : this()

   constructor(precioDia: Int, gps: Boolean, type: String) : this()

}
