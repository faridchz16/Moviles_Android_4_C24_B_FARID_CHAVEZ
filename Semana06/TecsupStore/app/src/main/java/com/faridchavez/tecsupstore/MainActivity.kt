package com.faridchavez.tecsupstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.faridchavez.tecsupstore.components.Producto
import com.faridchavez.tecsupstore.components.TarjetaProducto
import com.faridchavez.tecsupstore.ui.theme.TecsupStoreTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TecsupStoreTheme {
                val productos = remember {
                    mutableStateListOf(
                        Producto(1, "Audifonos", 89.00),
                        Producto(2, "Smartwatch", 199.00),
                        Producto(3, "Funda celular", 25.00)
                    )
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Column {
                                    Text("TECSUP Store", color = Color.White)
                                    Text("Mas vendidos", style = MaterialTheme.typography.bodySmall, color = Color(0xFFD1C4E9))
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF5E35B1)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(productos, key = { it.id }) { producto ->
                            TarjetaProducto(
                                producto = producto,
                                onToggleFavorito = { prodModificado ->
                                    val index = productos.indexOfFirst { it.id == prodModificado.id }
                                    if (index != -1) {
                                        productos[index] = productos[index].copy(
                                            esFavorito = !productos[index].esFavorito
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}