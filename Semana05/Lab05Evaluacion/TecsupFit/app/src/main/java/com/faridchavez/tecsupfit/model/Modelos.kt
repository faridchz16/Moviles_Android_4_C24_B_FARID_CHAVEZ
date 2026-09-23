package com.faridchavez.tecsupfit.model

import androidx.compose.ui.graphics.Color

val VerdePrincipal = Color(0xFF0B6046)
val VerdeFondoCard = Color(0xFFE2F3EB)
val VerdeAcento = Color(0xFF1CB57A)
val GrisFondo = Color(0xFFF7F9F8)
val GrisTexto = Color(0xFF757575)

data class ClaseFit(
    val id: Int,
    val titulo: String,
    val horario: String,
    val sala: String,
    val duracionMin: Int,
    val descripcion: String,
    val cuposDisponibles: Int,
    val cuposTotales: Int,
    val periodo: String
)

data class Reserva(
    val id: Int,
    val clase: ClaseFit,
    val estado: String
)