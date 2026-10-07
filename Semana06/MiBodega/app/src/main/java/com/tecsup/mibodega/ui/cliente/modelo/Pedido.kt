package com.tecsup.mibodega.ui.cliente.modelo

data class Pedido(
    val id: String,
    val items: List<ItemCarrito>,
    val total: Double,
    val fecha: String,
    val tipoEntrega: String,
    val direccion: String,
    val metodoPago: String
)

object RepositorioPedidos {
    val historial = mutableListOf<Pedido>()
    var ultimoPedido: Pedido? = null

    fun agregarPedido(pedido: Pedido) {
        ultimoPedido = pedido
        historial.add(0, pedido)
    }
}