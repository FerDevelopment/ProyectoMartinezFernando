package com.example.proyectomartinezfernando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectomartinezfernando.ui.theme.ProyectoMartinezFernandoTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         ProyectoMartinezFernandoTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               Prueba(modifier = Modifier.padding(innerPadding))
            }
         }
      }
   }

   @Composable
   fun Prueba(modifier: Modifier = Modifier) {
      MyApp(modifier)
   }

   @Composable
   fun MyApp(modifier: Modifier) {
      // Creamos el NavController
      val navController = rememberNavController()
      // Creamos el NavHost que define todas las pantallas/rutas de la app
      NavHost(
         navController = navController,
         startDestination = "pantalla_inicial"
      ) {
         composable("pantalla_inicial") {
            PantallaInicial(
               modifier = modifier,
               navController = navController
            )
         }
         composable("crear_pedido") { CrearPedido(modifier, navController) }
         composable("formulario_pago") { FormularioPago(modifier, navController) }
         composable("lista_pedidos") { ListaPedidos(modifier, navController) }
         composable("resumen_pedido") { ResumenPedido(modifier, navController) }
         composable("resumen_pago") { ResumenPago(modifier, navController) }
      }
   }

}

