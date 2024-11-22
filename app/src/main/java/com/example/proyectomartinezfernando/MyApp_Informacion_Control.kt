package com.example.proyectomartinezfernando

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectomartinezfernando.ui.viewmodel.PedidoViewModel

enum class Pantallas(
        @StringRes
        val titulo : Int
                    ) {
    Inicio(titulo = R.string.usuarioInfo) ,
    CrearPedido(titulo = R.string.hacer_pedido) ,
    FormularioPago(titulo = R.string.formulario_de_pago) ,
    ListaPedidos(titulo = R.string.lista_pedidos) ,
    ResumenPedido(titulo = R.string.resumen_pedido) ,
    ResumenPago(titulo = R.string.resumen_de_pago)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppTopBar(
        pantallaActual : Pantallas ,
        puedeNavegarAtras : Boolean ,
        onNavegarAtras : () -> Unit ,
        modifier : Modifier = Modifier
                     ) {
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) } ,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
                                                        ) ,
        navigationIcon = {
            if (puedeNavegarAtras) {
                IconButton(
                    onClick = onNavegarAtras
                          ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                        contentDescription = stringResource(R.string.ir_atras)
                        )
                }
            }
        }
             )

}

@Composable
fun MyApp(
        modifier : Modifier = Modifier ,
        pedidoViewModel : PedidoViewModel = viewModel()
         ) {
    val pedidoUIState by pedidoViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val pilaRetroceso by navController.currentBackStackEntryAsState()
    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
                                          )
    val volverAtras : () -> Unit = {
        navController.navigateUp()
    }
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
                    onVolverInicio = volverAtras ,
                    onIrResumenPedido = {
                        navController.navigate(Pantallas.ResumenPedido.name)
                    } ,
                    onElegirDiasAlquiler = { pedidoViewModel.actualizarDias(it) } ,
                    onElegirVehiculo = { pedidoViewModel.actualizarVehiculo(it) } ,
                    precioFinal = pedidoUIState.totalPagar ,
                    gps = pedidoUIState.gps ,
                    onElegirGPS = {
                        pedidoViewModel.actualizarGPS(it)
                    } ,
                    dias = pedidoUIState.dias.toString() ,
                    vehiculo = pedidoUIState.vehiculo
                           )
            }
            composable(Pantallas.FormularioPago.name) {
                FormularioPago(modifier , onVolverResumenPedido = volverAtras ,
                    onIrResumenPago =
                    {
                        navController.navigate(Pantallas.ResumenPago.name)
                    } ,
                    onAgregarTarjeta = { pedidoViewModel.actualizarTarjeta(it) }
                              )
            }
            composable(Pantallas.ListaPedidos.name) {
                ListaPedidos(
                    modifier ,
                    onVolverInicio = volverAtras
                            )
            }
            composable(Pantallas.ResumenPedido.name) {
                ResumenPedido(
                    modifier ,
                    onVolverCrearPedido = volverAtras ,
                    onIrFomularioPago = { navController.navigate(Pantallas.FormularioPago.name) } ,
                    pedido = pedidoUIState)
            }
            composable(Pantallas.ResumenPago.name) {
                ResumenPago(
                    modifier = modifier ,
                    onVolverFormularioPago = volverAtras ,
                    onPagar = {
                        navController.popBackStack(
                            Pantallas.Inicio.name ,
                            inclusive = false
                                                  )
                    } ,
                    tarjeta = pedidoUIState.tarjeta
                           )
            }
        }
    }
}

