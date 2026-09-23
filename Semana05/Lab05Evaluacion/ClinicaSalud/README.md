# Clínica Salud+
App de reserva de citas médicas — Tarea integradora Semanas 1 a 6 (sin MVVM).
Curso: Programación en Móviles — Docente: Juan León S.
Alumno: Farid Chávez

---

## Rubro elegido
Opción A — Clínica Salud+ (Reserva de citas médicas, navegación secundaria con Drawer).

---

## Requisitos funcionales implementados

• **RF01 - Catálogo y filtrado de médicos (HomeScreen):** LazyRow con chips de especialidad (Todas, Cardiología, Pediatría, Dermatología) para filtrar interactivamente y LazyColumn con la lista de médicos disponibles (médicos de ejemplo), cada tarjeta con su nombre, especialidad y calificación.

• **RF02 - Perfil del médico y flujo secuencial (MedicoDetailScreen):** Recibe el médico elegido por parámetro de navegación (medicoId: Int), muestra su información detallada y dispone del botón "Agendar cita" para continuar el flujo.

• **RF03 - Selección de cita y confirmación (AgendarCitaScreen / ConfirmacionScreen):** Selección de fecha (3 opciones) y hora (3 opciones) de selección única en LazyRow (operan conceptualmente como RadioButtons) con botón "Confirmar cita"; pantalla de confirmación con check verde, resumen de la cita (médico, fecha, hora), guardado en memoria y botón "Ver mis citas".

• **RF04 - Menú lateral y gestión de citas (ClinicaApp / CitasScreen / HistorialMedicoScreen / PerfilScreen):** Menú lateral (Drawer) con ícono en la topBar y 4 destinos funcionales: Inicio, Mis citas (LazyColumn con estados "Confirmada" / "Completada" diferenciados por color), Historial médico (lista de atenciones clínicas previas) y Perfil (datos del paciente).

---

## Metodología y Control de Versiones

• **Rama main (Fase 1 - SIN IA):** Implementación completa del flujo base utilizando únicamente remember y mutableStateOf (mínimo 8 commits).
• **Rama mejora-ia (Fase 2 - CON IA):** Incorporación de mejoras funcionales asistidas por IA y archivo PROMPTS.md de documentación (mínimo 3 commits).