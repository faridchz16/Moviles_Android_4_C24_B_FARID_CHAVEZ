package com.faridchavez.carritopoo

fun main() {

    val producto1 = Electronico(
        "Laptop",
        2500.00,
        1
    )

    val producto2 = Accesorio(
        "Mouse",
        50.00,
        2
    )

    println("========================================")
    println(" CARRITO DE COMPRAS - POO ")
    println("========================================")

    println("Producto 1: ${producto1.nombre}")
    println("Precio: S/ ${producto1.precio}")
    println("Cantidad: ${producto1.cantidad}")

    println()

    println("Producto 2: ${producto2.nombre}")
    println("Precio: S/ ${producto2.precio}")
    println("Cantidad: ${producto2.cantidad}")
}