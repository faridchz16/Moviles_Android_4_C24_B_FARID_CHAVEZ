package com.faridchavez.tecsupstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupstore.components.AppDrawerContent
import com.faridchavez.tecsupstore.components.Producto
import com.faridchavez.tecsupstore.components.TarjetaProducto
import com.faridchavez.tecsupstore.ui.theme.TecsupStoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TecsupStoreTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var rutaSeleccionada by remember { mutableStateOf("inicio") }

                val productos = remember {
                    mutableStateListOf(
                        Producto(1, "Audifonos", 89.00),
                        Producto(2, "Smartwatch", 199.00),
                        Producto(3, "Funda celular", 25.00)
                    )
                }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        AppDrawerContent(
                            rutaActual = rutaSeleccionada,
                            cantidadFavoritos = productos.count { it.esFavorito },
                            onNavegar = { ruta ->
                                rutaSeleccionada = ruta
                                scope.launch { drawerState.close() }
                            }
                        )
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Column {
                                        Text(
                                            text = "TECSUP Store",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                        Text(
                                            text = "Mas vendidos",
                                            color = Color(0xFFD1C4E9),
                                            fontSize = 12.sp
                                        )
                                    }
                                },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menu",
                                            tint = Color.White
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color(0xFF5E2B88)
                                )
                            )
                        },
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.White
                    ) { innerPadding ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .padding(top = 12.dp)
                        ) {
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
}