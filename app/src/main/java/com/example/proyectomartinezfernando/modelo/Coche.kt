package com.example.proyectomartinezfernando.modelo

class Coche(
   precioDia: Int = 0,
   gps: Boolean = false,
   type: String = ""
) : Vehiculo(precioDia, gps, type) {
   constructor(precioDia: String) : this()

}