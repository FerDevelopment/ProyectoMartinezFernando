package com.example.proyectomartinezfernando.data

import com.example.proyectomartinezfernando.modelo.User
import com.example.proyectomartinezfernando.modelo.Vehiculo

data class PedidoData(
        var vehiculo : Vehiculo = Vehiculo() ,
        var user : User = User() ,
        var dias : Int = 0 ,
        var totalPagar : Double = 0.0
                     )
