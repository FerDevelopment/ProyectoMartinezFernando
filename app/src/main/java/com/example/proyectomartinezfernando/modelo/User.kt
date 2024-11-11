package com.example.proyectomartinezfernando.modelo

import androidx.annotation.DrawableRes
import com.example.proyectomartinezfernando.R

class User() {
   val nombreUser: String = "Predeterminado"
   val correo: String = "Predeterminado@Predeterminado.com"
   val telefono: String = "123 45 67 89"
   val nombre: String = "Predeterminado"
   val apellido: String = "PredeterminadS"
   val pedidos: MutableList<Pedido> = mutableListOf()
   val info = listOf(
      this.nombre,
      this.apellido,
      this.telefono,
      this.nombreUser,
      this.correo
   )

   @DrawableRes
   val fotoPerfil: Int = R.drawable.default_perfil

   constructor(
      nombreUser: String,
      correo: String,
      telefono: String,
      nombre: String,
      apellido: String,
      @DrawableRes fotoPerfil: Int
   ) : this()

}
