package com.faridchavez.carritopoo

class Carrito {

    private val productos: MutableList<Producto> = mutableListOf()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun calcularSubtotal(): Double {
        return productos.sumOf { producto ->
            producto.calcularImporte()
        }
    }

    fun calcularIGV(): Double {
        return calcularSubtotal() * 0.18
    }

    fun calcularTotal(): Double {
        return calcularSubtotal() + calcularIGV()
    }

    fun calcularDescuento(): Double {
        val total = calcularTotal()

        return when {
            total > 5000 -> total * 0.10
            total > 3000 -> total * 0.05
            else -> 0.0
        }
    }

    fun obtenerProductoMasCaro(): Producto? {
        return productos.maxByOrNull { producto ->
            producto.precio
        }
    }

    fun mostrarDetalle() {
        println("--------- DETALLE DEL CARRITO ---------")

        var i = 1

        for (producto in productos) {
            println(
                String.format(
                    "%d. %-20s x%d  S/ %8.2f",
                    i,
                    producto.nombre,
                    producto.cantidad,
                    producto.calcularImporte()
                )
            )

            i++
        }

        println("---------------------------------------")
    }
}