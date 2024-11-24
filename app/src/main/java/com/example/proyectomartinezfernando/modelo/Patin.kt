package com.example.proyectomartinezfernando.modelo

import com.example.proyectomartinezfernando.R

class Patin(
   precioDia: Int = 0, gps: Boolean = false, type: String = ""
) : Vehiculo(precioDia, gps, type, imagen = R.drawable.patinete)