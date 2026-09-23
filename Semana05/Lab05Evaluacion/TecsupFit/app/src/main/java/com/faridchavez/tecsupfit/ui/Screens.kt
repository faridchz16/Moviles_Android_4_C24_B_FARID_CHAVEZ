package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.faridchavez.tecsupfit.model.ClaseFit

@Composable
fun HomeScreen(onSeleccionarClase: (ClaseFit) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { onSeleccionarClase(ClaseFit(id = "1", nombre = "Spinning", instructor = "Carlos", horario = "08:00 AM", cupos = 5)) }) {
            Text("Ver Clase de Ejemplo (Spinning)")
        }
    }
}

@Composable
fun ReservasScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Pantalla de Mis Reservas")
    }
}

@Composable
fun PerfilScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Pantalla de Perfil de Usuario")
    }
}

@Composable
fun DetalleClaseScreen(
    clase: ClaseFit,
    onVolver: () -> Unit,
    onReservar: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Detalle de Clase: ${clase.nombre}")
    }
}

@Composable
fun ConfirmacionScreen(
    clase: ClaseFit,
    onIrAMisReservas: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Reserva Confirmada para: ${clase.nombre}")
    }
}
