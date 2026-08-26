package com.faridchavez.carrito

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

fun calcularSubtotal(productos: List<Producto>): Double {
    var subtotal = 0.0

    for (p in productos) {
        subtotal += p.precio * p.cantidad
    }

    return subtotal
}

fun calcularIGV(subtotal: Double): Double {
    return subtotal * 0.18
}

fun calcularTotal(subtotal: Double, igv: Double): Double {
    return subtotal + igv
}

fun main() {

    println("========================================")
    println(" CARRITO DE COMPRAS - TIENDA TECSUP ")
    println("========================================")

    val nombreCliente = "Farid Chavez"
    println("Cliente: $nombreCliente")
    println()

    val productos = mutableListOf<Producto>()

    productos.add(Producto("Laptop", 2500.00, 1))
    productos.add(Producto("Mouse", 50.00, 2))
    productos.add(Producto("Teclado", 120.00, 1))
    productos.add(Producto("Audifonos", 180.00, 1))

    println("\nPRODUCTOS EN EL CARRITO:")
    println("----------------------------------------")

    for (producto in productos) {
        println("Producto: ${producto.nombre}")
        println("Precio: S/ ${producto.precio}")
        println("Cantidad: ${producto.cantidad}")
        println("----------------------------------------")
    }

    val subtotal = calcularSubtotal(productos)
    val igv = calcularIGV(subtotal)
    val total = calcularTotal(subtotal, igv)

    println()
    println("Subtotal: S/ $subtotal")
    println("IGV (18%): S/ $igv")
    println("TOTAL: S/ $total")
}