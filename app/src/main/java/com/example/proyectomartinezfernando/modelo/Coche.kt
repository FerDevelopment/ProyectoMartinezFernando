package com.example.proyectomartinezfernando.modelo

import com.example.proyectomartinezfernando.R

class Coche(
   precioDia: Int = 0, gps: Boolean = false, type: String = "die"
) : Vehiculo(precioDia, gps, type, imagen = R.drawable.coche)

