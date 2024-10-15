package com.example.proyectomartinezfernando.clases

class Pedido(
            ) {
    val vehiculo : Vehiculo = Vehiculo()
    val user : User = User()
    val dias : Int = 0

    constructor(
            vehiculo : Vehiculo ,
            user : User ,
            dias : Int
               ) : this()
}
