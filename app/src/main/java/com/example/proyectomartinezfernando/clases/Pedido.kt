package com.example.proyectomartinezfernando.clases

class Pedido(

) {
   var vehiculo: Vehiculo = Vehiculo()
   var user: User = User()
   var dias: Int = 0
   var totalPagar: Double = 0.0

   constructor(
      vehiculo: Vehiculo,
      user: User,
      dias: Int
   ) : this()

   constructor(user: User) : this()
}
