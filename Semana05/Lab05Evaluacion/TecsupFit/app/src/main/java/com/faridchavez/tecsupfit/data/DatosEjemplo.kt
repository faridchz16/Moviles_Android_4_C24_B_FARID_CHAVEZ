package com.faridchavez.tecsupfit.data

import androidx.compose.runtime.mutableStateListOf
import com.faridchavez.tecsupfit.model.ClaseFit
import com.faridchavez.tecsupfit.model.Reserva

val listaClases = listOf(
    ClaseFit(
        id = 1,
        titulo = "Yoga funcional",
        horario = "7:00 am",
        sala = "Sala 2",
        duracionMin = 50,
        descripcion = "Clase de flexibilidad, postura y respiración para activación matutina.",
        cuposDisponibles = 5,
        cuposTotales = 15,
        periodo = "Hoy"
    ),
    ClaseFit(
        id = 2,
        titulo = "Cross Training",
        horario = "6:00 pm",
        sala = "Sala 1",
        duracionMin = 45,
        descripcion = "Entrenamiento funcional de alta intensidad. Cupos limitados.",
        cuposDisponibles = 8,
        cuposTotales = 12,
        periodo = "Hoy"
    ),
    ClaseFit(
        id = 3,
        titulo = "Spinning",
        horario = "7:30 pm",
        sala = "Sala 3",
        duracionMin = 45,
        descripcion = "Sesión cardiovascular de ciclismo bajo techo con ritmo dinámico.",
        cuposDisponibles = 4,
        cuposTotales = 20,
        periodo = "Hoy"
    ),
    ClaseFit(
        id = 4,
        titulo = "Pilates Mat",
        horario = "8:00 am",
        sala = "Sala 2",
        duracionMin = 50,
        descripcion = "Fortalecimiento de la musculatura profunda y control corporal.",
        cuposDisponibles = 6,
        cuposTotales = 12,
        periodo = "Esta semana"
    )
)

val misReservasGlobales = mutableStateListOf(
    Reserva(1, listaClases[1], "Confirmada"),
    Reserva(2, listaClases[0], "Completada")
)