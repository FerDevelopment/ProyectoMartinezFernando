package com.example.proyectomartinezfernando.modelo

import com.example.proyectomartinezfernando.datos.CargarDatos

class Moto(
        precioDia : Int = 0 , gps : Boolean = false , type : String = "250"
          ) : Vehiculo(precioDia , gps , type) {}