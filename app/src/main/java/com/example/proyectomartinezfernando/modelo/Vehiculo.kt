package com.example.proyectomartinezfernando.modelo

import androidx.annotation.DrawableRes
import com.example.proyectomartinezfernando.R

open class Vehiculo(
   var precioDia: Int = 1, var gps: Boolean = false, var type: String = "die",
   @DrawableRes
   var imagen: Int = R.drawable.coche
) {
   fun info(): List<String> = listOf(
      if (gps) {
         "con gps"
      } else {
         "sin gps"
      }, type, precioDia.toString()
   )

}
