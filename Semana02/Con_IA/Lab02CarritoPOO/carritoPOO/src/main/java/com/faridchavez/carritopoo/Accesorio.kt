package com.faridchavez.carritopoo

class Accesorio(
    nombre: String,
    precio: Double,
    cantidad: Int
) : Producto(nombre, precio, cantidad) {

    override fun calcularImporte(): Double {
        return precio * cantidad
    }
}