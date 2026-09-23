package com.faridchavez.tecsupfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.faridchavez.tecsupfit.model.ClaseFit
import com.faridchavez.tecsupfit.ui.BottomBar
import com.faridchavez.tecsupfit.ui.ConfirmacionScreen
import com.faridchavez.tecsupfit.ui.DetalleClaseScreen
import com.faridchavez.tecsupfit.ui.HomeScreen
import com.faridchavez.tecsupfit.ui.PerfilScreen
import com.faridchavez.tecsupfit.ui.ReservasScreen
import com.faridchavez.tecsupfit.ui.RutinasScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var pantallaActual by remember { mutableStateOf("inicio") }
            var claseSeleccionada by remember { mutableStateOf<ClaseFit?>(null) }

            Scaffold(
                bottomBar = {
                    BottomBar(
                        pantallaActual = pantallaActual,
                        onNavegar = { nuevaPantalla ->
                            pantallaActual = nuevaPantalla
                        }
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    when (pantallaActual) {
                        "inicio" -> HomeScreen(
                            onSeleccionarClase = { clase ->
                                claseSeleccionada = clase
                                pantallaActual = "detalle"
                            }
                        )
                        "detalle" -> claseSeleccionada?.let { clase ->
                            DetalleClaseScreen(
                                clase = clase,
                                onVolver = { pantallaActual = "inicio" },
                                onReservar = { pantallaActual = "confirmacion" }
                            )
                        }
                        "confirmacion" -> claseSeleccionada?.let { clase ->
                            ConfirmacionScreen(
                                clase = clase,
                                onIrAMisReservas = { pantallaActual = "reservas" }
                            )
                        }
                        "reservas" -> ReservasScreen()
                        "rutinas" -> RutinasScreen()
                        "perfil" -> PerfilScreen()
                    }
                }
            }
        }
    }
}