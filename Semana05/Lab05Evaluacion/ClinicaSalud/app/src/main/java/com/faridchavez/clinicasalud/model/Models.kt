package com.faridchavez.clinicasalud.model

data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Double,
    val descripcion: String
)

data class Cita(
    val id: Int,
    val medicoNombre: String,
    val especialidad: String,
    val fecha: String,
    val hora: String,
    val estado: String
)