package com.tecsup.mibodega.ui.cliente.modelo

import androidx.annotation.DrawableRes

data class Producto(
    val id: Int,
    val nombre: String,
    val presentacion: String,
    val precio: Double,
    val categoria: String,
    val descripcion: String,
    @DrawableRes val imagenRes: Int
)