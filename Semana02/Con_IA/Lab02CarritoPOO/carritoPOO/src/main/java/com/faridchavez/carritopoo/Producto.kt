package com.faridchavez.carritopoo

open class Producto(
    val nombre: String,
    val precio: Double,
    val cantidad: Int
) {

    open fun calcularImporte(): Double {
        return precio * cantidad
    }
}