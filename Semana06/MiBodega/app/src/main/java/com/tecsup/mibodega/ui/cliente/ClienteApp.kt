package com.tecsup.mibodega.ui.cliente

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tecsup.mibodega.ui.cliente.modelo.ItemCarrito
import com.tecsup.mibodega.ui.cliente.modelo.Producto
import com.tecsup.mibodega.ui.cliente.modelo.listaProductosFake
import com.tecsup.mibodega.ui.cliente.screens.bienvenida.BienvenidaScreen
import com.tecsup.mibodega.ui.cliente.screens.carrito.CarritoScreen
import com.tecsup.mibodega.ui.cliente.screens.confirmacion.ConfirmacionScreen
import com.tecsup.mibodega.ui.cliente.screens.detalle.DetalleProductoScreen
import com.tecsup.mibodega.ui.cliente.screens.entrega.DatosEntregaScreen
import com.tecsup.mibodega.ui.cliente.screens.inicio.InicioScreen
import com.tecsup.mibodega.ui.cliente.screens.registro.RegistroScreen

object Rutas {
    const val BIENVENIDA = "bienvenida"
    const val REGISTRO = "registro"
    const val INICIO = "inicio"
    const val DETALLE = "detalle/{productoId}"
    const val CARRITO = "carrito"
    const val ENTREGA = "entrega"
    const val CONFIRMACION = "confirmacion"

    fun detalle(productoId: Int) = "detalle/$productoId"
}

@Composable
fun ClienteApp() {
    val navController = rememberNavController()
    var carrito by remember { mutableStateOf(listOf<ItemCarrito>()) }

    NavHost(
        navController = navController,
        startDestination = Rutas.BIENVENIDA
    ) {
        composable(Rutas.BIENVENIDA) {
            BienvenidaScreen(
                onRegistrarse = { navController.navigate(Rutas.REGISTRO) },
                onIniciarSesion = {
                    navController.navigate(Rutas.INICIO) {
                        popUpTo(Rutas.BIENVENIDA) { inclusive = true }
                    }
                },
                onTerminos = { }
            )
        }

        composable(Rutas.REGISTRO) {
            RegistroScreen(
                onVolver = { navController.popBackStack() },
                onCrearCuenta = { _, _, _, _ ->
                    navController.navigate(Rutas.INICIO) {
                        popUpTo(Rutas.BIENVENIDA) { inclusive = true }
                    }
                }
            )
        }

        composable(Rutas.INICIO) {
            InicioScreen(
                cantidadCarrito = carrito.sumOf { it.cantidad },
                onVerCarrito = { navController.navigate(Rutas.CARRITO) },
                onProductoClick = { producto ->
                    navController.navigate(Rutas.detalle(producto.id))
                },
                onAgregarProducto = { producto ->
                    carrito = agregarOSumarProducto(carrito, producto, 1)
                },
                onCerrarSesion = {
                    carrito = emptyList()
                    navController.navigate(Rutas.BIENVENIDA) {
                        popUpTo(Rutas.INICIO) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Rutas.DETALLE,
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getInt("productoId") ?: -1
            val producto = listaProductosFake.firstOrNull { it.id == productoId }
                ?: listaProductosFake.first()

            DetalleProductoScreen(
                producto = producto,
                onVolver = { navController.popBackStack() },
                onAgregarAlCarrito = { prod, cant ->
                    carrito = agregarOSumarProducto(carrito, prod, cant)
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.CARRITO) {
            CarritoScreen(
                carrito = carrito,
                onVolver = { navController.popBackStack() },
                onIncrementar = { producto ->
                    carrito = sumarUno(carrito, producto)
                },
                onDecrementar = { producto ->
                    carrito = restarUno(carrito, producto)
                },
                onEliminar = { producto ->
                    carrito = eliminarProducto(carrito, producto)
                },
                onContinuarPedido = {
                    navController.navigate(Rutas.ENTREGA)
                }
            )
        }

        composable(Rutas.ENTREGA) {
            DatosEntregaScreen(
                onVolver = { navController.popBackStack() },
                onConfirmarPedido = {
                    navController.navigate(Rutas.CONFIRMACION)
                }
            )
        }

        composable(Rutas.CONFIRMACION) {
            ConfirmacionScreen(
                onVolverInicio = {
                    carrito = emptyList()
                    navController.navigate(Rutas.INICIO) {
                        popUpTo(Rutas.INICIO) { inclusive = true }
                    }
                }
            )
        }
    }
}

private fun agregarOSumarProducto(
    lista: List<ItemCarrito>,
    producto: Producto,
    cantidad: Int
): List<ItemCarrito> {
    val copia = lista.toMutableList()
    val index = copia.indexOfFirst { it.producto.id == producto.id }
    if (index >= 0) {
        val actual = copia[index]
        copia[index] = actual.copy(cantidad = actual.cantidad + cantidad)
    } else {
        copia.add(ItemCarrito(producto = producto, cantidad = cantidad))
    }
    return copia
}

private fun sumarUno(lista: List<ItemCarrito>, producto: Producto): List<ItemCarrito> {
    return lista.map { item ->
        if (item.producto.id == producto.id) item.copy(cantidad = item.cantidad + 1) else item
    }
}

private fun restarUno(lista: List<ItemCarrito>, producto: Producto): List<ItemCarrito> {
    return lista.mapNotNull { item ->
        if (item.producto.id == producto.id) {
            if (item.cantidad > 1) item.copy(cantidad = item.cantidad - 1) else null
        } else {
            item
        }
    }
}

private fun eliminarProducto(lista: List<ItemCarrito>, producto: Producto): List<ItemCarrito> {
    return lista.filterNot { it.producto.id == producto.id }
}