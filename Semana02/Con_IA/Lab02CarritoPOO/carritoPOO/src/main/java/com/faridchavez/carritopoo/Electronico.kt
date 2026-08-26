package com.faridchavez.carritopoo

class Electronico(
    nombre: String,
    precio: Double,
    cantidad: Int
) : Producto(nombre, precio, cantidad) {

    override fun calcularImporte(): Double {
        return precio * cantidad * 1.05
    }
}