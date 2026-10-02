package com.tecsup.mibodega.ui.cliente.modelo

import com.tecsup.mibodega.R

val listaCategorias = listOf("Todos", "Bebidas", "Abarrotes", "Snacks")

val listaProductosFake = listOf(
    Producto(
        id = 1,
        nombre = "Arroz Costeño",
        presentacion = "1 kg",
        precio = 4.50,
        categoria = "Abarrotes",
        descripcion = "Arroz extra blanco de grano seleccionado, ideal para tus comidas familiares.",
        imagenRes = R.drawable.arroz_costeno
    ),
    Producto(
        id = 2,
        nombre = "Aceite Primor",
        presentacion = "1 L",
        precio = 8.90,
        categoria = "Abarrotes",
        descripcion = "Aceite vegetal premium 100% puro para todo tipo de preparaciones.",
        imagenRes = R.drawable.aceite_primor
    ),
    Producto(
        id = 3,
        nombre = "Leche Gloria",
        presentacion = "1 L",
        precio = 5.20,
        categoria = "Abarrotes",
        descripcion = "Leche evaporada entera enriquecida con vitaminas A y D.",
        imagenRes = R.drawable.leche_gloria
    ),
    Producto(
        id = 4,
        nombre = "Galleta Oreo",
        presentacion = "126 g",
        precio = 3.50,
        categoria = "Snacks",
        descripcion = "Galletas crocantes sabor chocolate rellenas de deliciosa crema dulce.",
        imagenRes = R.drawable.galleta_oreo
    ),
    Producto(
        id = 5,
        nombre = "Coca-Cola Original",
        presentacion = "1.5 L",
        precio = 6.50,
        categoria = "Bebidas",
        descripcion = "Bebida gaseosa sabor cola. Ideal para compartir en familia.",
        imagenRes = R.drawable.coca_cola
    )
)