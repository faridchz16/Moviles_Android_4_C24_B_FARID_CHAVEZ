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

fun mostrarDetalle(productos: List<Producto>) {
    println("--------- DETALLE DEL CARRITO ---------")

    var i = 1

    for (p in productos) {
        val importe = p.precio * p.cantidad

        println(
            String.format(
                "%d. %-20s x%d  S/ %8.2f",
                i, p.nombre, p.cantidad, importe
            )
        )

        i++
    }

    println("---------------------------------------")
}

fun calcularDescuento(total: Double): Double {
    return when {
        total > 5000 -> total * 0.10
        total > 3000 -> total * 0.05
        else -> 0.0
    }
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

    mostrarDetalle(productos)

    println("Cantidad de productos: ${productos.size}")

    val masCaro = productos.maxByOrNull { it.precio }

    if (masCaro != null) {
        println(
            "Producto mas caro: ${masCaro.nombre} " +
                    String.format("(S/ %.2f)", masCaro.precio)
        )
    }

    val subtotal = calcularSubtotal(productos)
    val igv = calcularIGV(subtotal)
    val total = calcularTotal(subtotal, igv)

    println()

    println(String.format("Subtotal       : S/ %8.2f", subtotal))
    println(String.format("IGV (18%%)     : S/ %8.2f", igv))
    println(String.format("TOTAL A PAGAR  : S/ %8.2f", total))

    val descuento = calcularDescuento(total)
    val totalConDescuento = total - descuento

    println()

    if (descuento > 0) {
        println(String.format("Descuento aplicado : S/ %.2f", descuento))
        println(
            String.format(
                "TOTAL CON DESCUENTO : S/ %.2f",
                totalConDescuento
            )
        )
    } else {
        println("No se aplica descuento")
    }
}