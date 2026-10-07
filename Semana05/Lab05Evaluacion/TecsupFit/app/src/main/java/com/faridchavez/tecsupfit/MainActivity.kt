package com.faridchavez.tecsupfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.model.ClaseFit
import com.faridchavez.tecsupfit.ui.ConfirmacionScreen
import com.faridchavez.tecsupfit.ui.DetalleClaseScreen
import com.faridchavez.tecsupfit.ui.HomeScreen
import com.faridchavez.tecsupfit.ui.PerfilScreen
import com.faridchavez.tecsupfit.ui.ReservasScreen

private val VerdeTecsup = Color(0xFF0D634C)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var tabActual by remember { mutableStateOf("Inicio") }
            var pantallaActual by remember { mutableStateOf("inicio") }
            var claseSeleccionada by remember { mutableStateOf<ClaseFit?>(null) }

            // Lista mutable reactiva compartida
            val misReservasGlobales = remember { mutableStateListOf<ClaseFit>() }

            Scaffold(
                bottomBar = {
                    if (pantallaActual == "inicio") {
                        NavigationBar(
                            containerColor = Color.White,
                            tonalElevation = 8.dp
                        ) {
                            val items = listOf(
                                Triple("Inicio", Icons.Default.Home, "Inicio"),
                                Triple("Reservas", Icons.Default.CalendarToday, "Reservas"),
                                Triple("Rutinas", Icons.Default.FitnessCenter, "Rutinas"),
                                Triple("Perfil", Icons.Default.Person, "Perfil")
                            )
                            items.forEach { (titulo, icono, ruta) ->
                                NavigationBarItem(
                                    selected = tabActual == ruta,
                                    onClick = {
                                        tabActual = ruta
                                        pantallaActual = "inicio"
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = icono,
                                            contentDescription = titulo,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    label = { Text(titulo, fontSize = 11.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = VerdeTecsup,
                                        selectedTextColor = VerdeTecsup,
                                        indicatorColor = VerdeTecsup.copy(alpha = 0.12f),
                                        unselectedIconColor = Color.Gray,
                                        unselectedTextColor = Color.Gray
                                    )
                                )
                            }
                        }
                    }
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    when (pantallaActual) {
                        "detalle" -> {
                            claseSeleccionada?.let { clase ->
                                DetalleClaseScreen(
                                    clase = clase,
                                    onVolver = { pantallaActual = "inicio" },
                                    onReservar = {
                                        // Agrega la clase a la lista global mutable
                                        if (misReservasGlobales.none { it.id == clase.id }) {
                                            misReservasGlobales.add(0, clase)
                                        }
                                        pantallaActual = "confirmacion"
                                    }
                                )
                            }
                        }
                        "confirmacion" -> {
                            ConfirmacionScreen(
                                clase = claseSeleccionada,
                                onIrAMisReservas = {
                                    tabActual = "Reservas"
                                    pantallaActual = "inicio"
                                }
                            )
                        }
                        else -> {
                            when (tabActual) {
                                "Inicio" -> HomeScreen(
                                    onSeleccionarClase = { clase ->
                                        claseSeleccionada = clase
                                        pantallaActual = "detalle"
                                    }
                                )
                                // Pasamos explícitamente la lista mutable
                                "Reservas" -> ReservasScreen(reservas = misReservasGlobales)
                                "Perfil" -> PerfilScreen()
                                else -> HomeScreen(
                                    onSeleccionarClase = { clase ->
                                        claseSeleccionada = clase
                                        pantallaActual = "detalle"
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