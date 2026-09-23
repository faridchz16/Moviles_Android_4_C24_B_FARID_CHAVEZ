package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Rutina(
    val titulo: String,
    val nivel: String,
    val duracion: String,
    val ejercicios: String
)

val listaRutinasMock = listOf(
    Rutina("Full Body Express", "Intermedio", "30 min", "6 ejercicios"),
    Rutina("Cárdio & Hiit", "Avanzado", "20 min", "5 ejercicios"),
    Rutina("Fuerza e Hipertrofia", "Principiante", "45 min", "8 ejercicios"),
    Rutina("Core & Abdominales", "Todos los niveles", "15 min", "4 ejercicios")
)

@Composable
fun RutinasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rutinas recomendadas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listaRutinasMock) { rutina ->
                CardRutina(rutina = rutina)
            }
        }
    }
}

@Composable
private fun CardRutina(rutina: Rutina) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.FitnessCenter,
                    contentDescription = null,
                    tint = Color(0xFF00695C),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = rutina.titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${rutina.nivel} · ${rutina.duracion}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = rutina.ejercicios,
                    color = Color(0xFF00695C),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            IconButton(onClick = { /* Acción para iniciar rutina */ }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Iniciar",
                    tint = Color(0xFF00695C)
                )
            }
        }
    }
}