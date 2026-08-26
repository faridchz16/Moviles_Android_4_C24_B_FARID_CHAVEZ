package com.faridchavez.carritopoo

fun main() {

    val productos = mutableListOf<Producto>()

    productos.add(
        Electronico(
            "Laptop",
            2500.00,
            1
        )
    )

    productos.add(
        Accesorio(
            "Mouse",
            50.00,
            2
        )
    )

    productos.add(
        Accesorio(
            "Teclado",
            120.00,
            1
        )
    )

    productos.add(
        Electronico(
            "Monitor",
            800.00,
            1
        )
    )

    println("========================================")
    println(" CARRITO DE COMPRAS - POO ")
    println("========================================")

    for (producto in productos) {
        println("Producto: ${producto.nombre}")
        println("Precio: S/ ${producto.precio}")
        println("Cantidad: ${producto.cantidad}")
        println("Importe: S/ ${producto.calcularImporte()}")
        println("----------------------------------------")
    }
}