package com.faridchavez.carritopoo

fun main() {

    val carrito = Carrito()

    carrito.agregarProducto(
        Electronico("Laptop", 2500.00, 1)
    )

    carrito.agregarProducto(
        Accesorio("Mouse", 50.00, 2)
    )

    carrito.agregarProducto(
        Accesorio("Teclado", 120.00, 1)
    )

    carrito.agregarProducto(
        Electronico("Monitor", 800.00, 1)
    )

    println("========================================")
    println(" CARRITO DE COMPRAS - POO ")
    println("========================================")
    println()

    carrito.mostrarDetalle()

    val subtotal = carrito.calcularSubtotal()
    val igv = carrito.calcularIGV()
    val total = carrito.calcularTotal()
    val descuento = carrito.calcularDescuento()
    val totalFinal = total - descuento

    val productoMasCaro = carrito.obtenerProductoMasCaro()

    if (productoMasCaro != null) {
        println()
        println("Producto mas caro: ${productoMasCaro.nombre}")
        println(
            String.format(
                "Precio del producto mas caro: S/ %.2f",
                productoMasCaro.precio
            )
        )
    }

    println()

    println(String.format("Subtotal            : S/ %8.2f", subtotal))
    println(String.format("IGV (18%%)           : S/ %8.2f", igv))
    println(String.format("TOTAL               : S/ %8.2f", total))
    println(String.format("Descuento           : S/ %8.2f", descuento))
    println(String.format("TOTAL CON DESCUENTO : S/ %8.2f", totalFinal))
}