package com.example.proyectomartinezfernando.modelo

import com.example.proyectomartinezfernando.R

class Moto(
   precioDia: Int = 0, gps: Boolean = false, type: String = "250"
) : Vehiculo(precioDia, gps, type, imagen = R.drawable.moto)