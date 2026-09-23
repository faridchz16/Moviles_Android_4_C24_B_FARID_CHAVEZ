package com.faridchavez.tecsupfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.faridchavez.tecsupfit.data.misReservasGlobales
import com.faridchavez.tecsupfit.model.ClaseFit
import com.faridchavez.tecsupfit.model.Reserva
import com.faridchavez.tecsupfit.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var tabActual by remember { mutableStateOf("Inicio") }
            var pantallaActual by remember { mutableStateOf("principal") }
            var claseSeleccionada by remember { mutableStateOf<ClaseFit?>(null) }

            Scaffold(
                bottomBar = {
                    if (pantallaActual == "principal") {
                        CustomBottomBar(
                            tabActual = tabActual,
                            onTabSeleccionado = { tabActual = it }
                        )
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
                                    onVolver = { pantallaActual = "principal" },
                                    onReservar = { claseReservada ->
                                        val nuevaReserva = Reserva(
                                            id = misReservasGlobales.size + 1,
                                            clase = claseReservada,
                                            estado = "Confirmada"
                                        )
                                        misReservasGlobales.add(0, nuevaReserva)
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
                                    pantallaActual = "principal"
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
                                "Reservas" -> ReservasScreen()
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