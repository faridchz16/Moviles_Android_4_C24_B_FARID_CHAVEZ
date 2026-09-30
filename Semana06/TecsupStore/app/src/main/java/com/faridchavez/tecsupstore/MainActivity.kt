package com.faridchavez.tecsupstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.faridchavez.tecsupstore.components.Producto
import com.faridchavez.tecsupstore.components.TarjetaProducto
import com.faridchavez.tecsupstore.ui.theme.TecsupStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TecsupStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        var productoEjemplo by remember {
                            mutableStateOf(Producto(1, "Audifonos", 89.00))
                        }

                        TarjetaProducto(
                            producto = productoEjemplo,
                            onToggleFavorito = { prod ->
                                productoEjemplo = productoEjemplo.copy(esFavorito = !productoEjemplo.esFavorito)
                            }
                        )
                    }
                }
            }
        }
    }
}