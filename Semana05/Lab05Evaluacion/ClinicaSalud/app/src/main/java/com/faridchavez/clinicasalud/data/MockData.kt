package com.faridchavez.clinicasalud.data

import com.faridchavez.clinicasalud.model.Cita
import com.faridchavez.clinicasalud.model.Medico

val especialidadesDisponibles = listOf(
    "Cardiología",
    "Pediatría",
    "Dermatología"
)

val listaMedicos = listOf(
    Medico(
        id = 1,
        nombre = "Dra. Ana Torres",
        especialidad = "Cardiología",
        calificacion = 4.9,
        descripcion = "Especialista en arritmias e hipertensión, formación en la Clínica Mayo"
    ),
    Medico(
        id = 2,
        nombre = "Dr. Luis Vega",
        especialidad = "Pediatría",
        calificacion = 4.7,
        descripcion = "Especialista en atención pediátrica integral y desarrollo infantil temprano"
    ),
    Medico(
        id = 3,
        nombre = "Dra. Rosa Díaz",
        especialidad = "Dermatología",
        calificacion = 4.8,
        descripcion = "Especialista en dermatología clínica, tratamiento del acné y cuidado de la piel"
    )
)

val listaCitasIniciales = listOf(
    Cita(
        id = 1,
        medicoNombre = "Dra. Ana Torres",
        especialidad = "Cardiología",
        fecha = "Viernes 27",
        hora = "10:30 am",
        estado = "Confirmada"
    ),
    Cita(
        id = 2,
        medicoNombre = "Dr. Luis Vega",
        especialidad = "Pediatría",
        fecha = "Miércoles 15",
        hora = "3:00 pm",
        estado = "Completada"
    )
)

val fechasDisponibles = listOf("Jue 26", "Vie 27", "Sáb 28")
val horasDisponibles = listOf("9:00", "10:30", "3:00")