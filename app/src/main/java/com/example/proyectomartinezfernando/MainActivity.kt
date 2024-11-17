package com.example.proyectomartinezfernando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectomartinezfernando.ui.theme.ProyectoMartinezFernandoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoMartinezFernandoTheme {
                MyApp()
            }
        }
    }

    enum class Pantallas(@StringRes val titulo : Int) {
        Inicio(titulo = R.string.usuario) ,
        CrearPedido(titulo = R.string.hacer_pedido) ,
        FormularioPago(titulo = R.string.formulario_de_pago) ,
        ListaPedidos(titulo = R.string.lista_pedidos) ,
        ResumenPedido(titulo = R.string.resumen_pedido) ,
        ResumenPago(titulo = R.string.resumen_de_pago)

    }

    @Composable
    fun Prueba(modifier : Modifier = Modifier) {

    }

    @Composable
    private fun AppTopBar(pantallaActual : Any , puedeNavegarAtras : Boolean , onNavegarAtras : Any) {

    }

    @Composable
    fun MyApp(modifier : Modifier = Modifier) {
        val navController = rememberNavController()
        val pilaRetroceso by navController.currentBackStackEntryAsState()
        val pantallaActual = Pantallas.valueOf(
            pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
                                              )
        // Creamos el NavController
        // Creamos el NavHost que define todas las pantallas/rutas de la app
        Scaffold(topBar = {
            AppTopBar(
                pantallaActual = pantallaActual ,
                puedeNavegarAtras = navController.previousBackStackEntry != null ,
                onNavegarAtras = { navController.navigateUp() }
                     )
        }) { innerPadding ->
            NavHost(
                navController = navController ,
                startDestination = Pantallas.Inicio.name ,
                modifier = modifier.padding(innerPadding)
                   ) {
                composable(Pantallas.Inicio.name) {
                    PantallaInicial(
                        modifier = modifier ,
                        onAbrirListaPedido = { navController.navigate(Pantallas.ListaPedidos.name) } ,
                        onCrearPedido = { navController.navigate(Pantallas.CrearPedido.name) }
                                   )
                }
                composable(Pantallas.CrearPedido.name) {
                    CrearPedido(
                        modifier = modifier ,
                        onVolverInicio = {
                            navController.navigate(
                                Pantallas.Inicio.name
                                                  )
                        } ,
                        onIrResumenPedido = {
                            navController.navigate(Pantallas.ResumenPedido.name)
                        }
                               )
                }
                composable(Pantallas.FormularioPago.name) {
                    FormularioPago(modifier , onVolverResumenPedido = {
                        navController.navigate(Pantallas.ResumenPedido.name)
                    } ,
                        onIrResumenPago =
                        {
                            navController.navigate(Pantallas.ResumenPago.name)
                        }
                                  )
                }
                composable(Pantallas.ListaPedidos.name) {
                    ListaPedidos(
                        modifier ,
                        onVolverInicio = { navController.navigate(Pantallas.Inicio.name) })
                }
                composable(Pantallas.ResumenPedido.name) {
                    ResumenPedido(
                        modifier ,
                        onCrearPedido = { navController.navigate(Pantallas.CrearPedido.name) } ,
                        onIrFomularioPago = { navController.navigate(Pantallas.FormularioPago.name) })
                }
                composable(Pantallas.ResumenPago.name) {
                    ResumenPago(
                        modifier = modifier ,
                        onVolverFormularioPago = { navController.navigate(Pantallas.FormularioPago.name) } ,
                        onPagar = { navController.navigate(Pantallas.Inicio.name) })
                }
            }
        }
    }

}

