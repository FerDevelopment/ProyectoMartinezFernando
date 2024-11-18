package com.example.proyectomartinezfernando.modelo

class Patin(
        precioDia : Int = 0 ,
        gps : Boolean = false ,
        type : String = ""
           ) : Vehiculo(precioDia , gps , type) {
    constructor(type : String) : this()
}