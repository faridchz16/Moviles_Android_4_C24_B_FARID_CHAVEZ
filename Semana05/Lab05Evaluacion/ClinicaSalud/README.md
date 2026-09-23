# Clínica Salud+
App de reserva de citas médicas - Tarea integradora Semanas 04 y 05  
**Curso:** Programación en Móviles  
**Docente:** Juan León S.  
**Alumno:** Farid Chávez

---

## Rubro Elegido
**Opción A – Clínica Salud+** (Reserva de citas médicas, navegación y gestión de citas).

---

## Metodología y Control de Versiones

* **Rama `main` (Fase 1 - SIN IA):** Implementación completa de la arquitectura base, navegación (`ModalNavigationDrawer`, pantallas secuenciales) y catálogo de datos.
* **Rama `mejora-ia` (Fase 2 - CON IA):** Incorporación de características avanzadas asistidas por IA (Gemini en Android Studio), registradas en commits incrementales con sus respectivos prompts.

---

## Mejoras Implementadas con Asistencia de IA (Gemini)

### 1. Búsqueda predictiva y filtros combinados en catálogo
* **Archivo intervenido:** `ui.HomeScreen.kt`
* **Commit:** `ca0824e`
* **Prompt utilizado:**
> En el archivo actual ui.HomeScreen.kt, implementa una barra de búsqueda predictiva y filtro combinado dentro de la función HomeScreen:
> 1. Declara la variable de estado para el texto de búsqueda: `var textoBusqueda by remember { mutableStateOf("") }`.
> 2. Modifica la lista `medicosFiltrados` para que filtre considerando la especialidad y el texto ingresado ignorando mayúsculas/minúsculas.
> 3. Añade el componente `OutlinedTextField` justo encima de la LazyRow de especialidades con iconos de búsqueda y botón para limpiar.
> 4. Si la lista queda vacía, muestra un mensaje indicando que no se encontraron resultados.

---

### 2. Cancelación de citas con diálogo interactivo de confirmación
* **Archivo intervenido:** `CitasScreen.kt`
* **Commit:** `4ca9ba1`
* **Prompt utilizado:**
> En el archivo actual CitasScreen.kt, implementa la funcionalidad para cancelar citas con diálogo de confirmación:
> 1. Mantén la lista de citas en un estado mutable local (`remember { mutableStateListOf(...) }`).
> 2. Agrega una variable de estado para controlar la cita a cancelar (`citaACancelar`).
> 3. En cada tarjeta añade un botón de acción para cancelar cita con icono Delete.
> 4. Al presionar, abre un `AlertDialog` de confirmación con opciones "Sí, cancelar" y "No, conservar".
> 5. Si la lista queda vacía, muestra un estado vacío informativo.

---

### 3. Validación de fecha/hora y retroalimentación al agendar
* **Archivo intervenido:** `MainActivity.kt` (o pantalla de agendamiento)
* **Commit:** *(Commit 4)*
* **Prompt utilizado:**
> En la pantalla de Agendar Cita (AgendarCitaScreen), implementa una mejora de experiencia de usuario con validación y notificación:
> 1. Antes de confirmar la reserva, valida que tanto la fecha como el horario hayan sido seleccionados.
> 2. Si falta algún dato, muestra un mensaje o Snackbar informativo indicando que complete la selección.
> 3. Si ambos datos están listos, confirma la reserva y emite la retroalimentación de éxito al usuario.