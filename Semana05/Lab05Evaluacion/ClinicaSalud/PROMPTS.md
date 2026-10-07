# Registro de Prompts - Fase 2: Mejora con Asistente de IA

* **Estudiante:** Farid Chávez
* **Curso:** Programación en Móviles 
* **Rama de trabajo:** `mejora-ia`
* **Herramienta:** Asistente Gemini (Android Studio)
* **Proyecto:** Clínica Salud+

---

## I. Diagnóstico y Alcance de la Mejora (Fase 2)
Implementar mejoras interactivas y de control reactivo sobre la arquitectura base de reserva de citas médicas:
1. **Búsqueda reactiva en tiempo real en `HomeScreen.kt`:** Integración de un campo de búsqueda con icono de lupa, botón de limpieza rápida (`Clear`) y filtrado simultáneo con las especialidades sin que se anulen entre sí.
2. **Cancelación interactiva de citas en `CitasScreen.kt`:** Incorporación de un botón de papelera en cada tarjeta y despliegue de un cuadro de diálogo modal `AlertDialog` con confirmación antes de eliminar el elemento de la lista.
3. **Validación estricta al agendar cita en `MainActivity.kt`:** Control previo en `AgendarCitaScreen` para evitar citas sin fecha u hora seleccionada, informando al usuario mediante una barra flotante inferior.

---

## II. Bitácora de Prompts y Soluciones Técnicas

### Prompt 1: Buscador en tiempo real combinado con categorías
* **Archivo modificado:** `app/src/main/java/com/faridchavez/clinicasalud/ui.HomeScreen.kt`
* **Mensaje de commit asociado:** `feat(ia): agregar buscador reactivo de medicos con filtro combinado`
* **Prompt formulado:**
  > *"Actúa como desarrollador senior de Android en Jetpack Compose. Tengo mi pantalla ui.HomeScreen.kt en la app ClinicaSalud con una lista LazyColumn de médicos y un LazyRow de especialidades ('Cardiología', 'Pediatría', 'Dermatología'). Genera el código exacto para implementar el buscador en tiempo real según el diseño de evaluación:*
  > *1. Agrega justo debajo del encabezado morado (Color(0xFF4A148C)) un OutlinedTextField con:*
  > *- placeholder: Text('Buscar médico por nombre...', fontSize = 13.sp, color = Color(0xFF7E768A))*
  > *- leadingIcon: Icon(Icons.Default.Search, tint = Color(0xFF7E768A))*
  > *- trailingIcon: Botón IconButton con Icon(Icons.Default.Clear) que aparezca solo si textoBusqueda.isNotEmpty() y que al pulsar limpie el texto a ''.*
  > *- shape: RoundedCornerShape(12.dp)*
  > *- colors: OutlinedTextFieldDefaults.colors con fondo blanco y borde enfocado Color(0xFF4A148C).*
  > *- modifier: Modifier.fillMaxWidth().padding(horizontal = 16.dp).*
  > *2. Maneja el estado con remember { mutableStateOf('') }.*
  > *3. La lista medicosFiltrados debe derivarse con remember(especialidadSeleccionada, textoBusqueda) evaluando:*
  > *val coincideEspecialidad = if (especialidadSeleccionada.isEmpty()) true else medico.especialidad.equals(especialidadSeleccionada, ignoreCase = true)*
  > *val coincideTexto = if (textoBusqueda.isBlank()) true else medico.nombre.contains(textoBusqueda.trim(), ignoreCase = true) || medico.especialidad.contains(textoBusqueda.trim(), ignoreCase = true)*
  > *coincideEspecialidad && coincideTexto*
  > *Dame el composable HomeScreen completo y listo para compilar sin errores de imports."*
* **Respuesta técnica de la IA:**  
  La IA implementó el estado observable `textoBusqueda` y estructuró la lista derivada combinando la especialidad y el texto mediante la conjunción `coincideEspecialidad && coincideTexto`. Añadió el `trailingIcon` condicionado a `textoBusqueda.isNotEmpty()` para restablecer el texto con un toque.
* **Ajuste aplicado:**  
  Se verificó la paleta institucional (`#4A148C`) y el radio de bordes para coincidir con la cabecera original.

---

### Prompt 2: Cancelación de citas con diálogo modal AlertDialog
* **Archivo modificado:** `app/src/main/java/com/faridchavez/clinicasalud/CitasScreen.kt`
* **Mensaje de commit asociado:** `feat(ia): implementar cancelacion de citas con AlertDialog`
* **Prompt formulado:**
  > *"En mi app ClinicaSalud tengo CitasScreen.kt con una lista LazyColumn de citas (List<Cita>). Necesito implementar la cancelación interactiva con diálogo modal:*
  > *1. Cambia el parámetro citas a MutableList<Cita> para poder eliminar elementos.*
  > *2. Agrega un estado observable: var citaParaEliminar by remember { mutableStateOf<Cita?>(null) }.*
  > *3. En cada tarjeta Card de la cita, agrega al final de la Row principal un IconButton con Icon(Icons.Default.Delete, tint = Color(0xFFE53935), contentDescription = 'Cancelar cita') que al hacer clic asigne: citaParaEliminar = cita.*
  > *4. Cuando citaParaEliminar != null, muestra un AlertDialog centrado con:*
  > *- title: Text('Cancelar Cita', fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF1E1926))*
  > *- text: Text('¿Estás seguro de que deseas cancelar la cita con el médico seleccionado?', fontSize = 14.sp, color = Color(0xFF555555))*
  > *- confirmButton: TextButton que al pulsar ejecute citas.remove(citaParaEliminar), limpie citaParaEliminar = null y muestre texto 'Sí, cancelar' con color Color(0xFF4A148C) y FontWeight.Bold.*
  > *- dismissButton: TextButton que al pulsar limpie citaParaEliminar = null y muestre texto 'No, conservar' con color Color(0xFF7E768A).*
  > *- shape: RoundedCornerShape(20.dp) y containerColor: Color.White.*
  > *Proporcióname el código completo de CitasScreen.kt."*
* **Respuesta técnica de la IA:**  
  La IA definió el estado `citaParaEliminar` y levantó condicionalmente el `AlertDialog`. Al presionar el botón de confirmación se remueve el objeto de la lista reactiva mutable, provocando la recomposición inmediata de la pantalla.
* **Ajuste aplicado:**  
  Se alinearon los colores de los textos de confirmación y descarte para mantener coherencia con el diseño de Material 3.

---

### Prompt 3: Validación interactiva del formulario al agendar
* **Archivo modificado:** `app/src/main/java/com/faridchavez/clinicasalud/MainActivity.kt`
* **Mensaje de commit asociado:** `feat(ia): implementar validacion de fecha y hora en AgendarCitaScreen`
* **Prompt formulado:**
  > *"En mi composable AgendarCitaScreen dentro de MainActivity.kt de ClinicaSalud, necesito implementar la validación de formulario interactiva:*
  > *1. Modifica diaSeleccionado y horaSeleccionada para que inicien vacíos: remember { mutableStateOf('') }.*
  > *2. Agrega una variable de estado: var mensajeAviso by remember { mutableStateOf<String?>(null) }.*
  > *3. Envuelve el contenido en un Box(modifier = Modifier.fillMaxSize()).*
  > *4. Al hacer clic en el botón Button('Confirmar cita'):*
  > *- Si diaSeleccionado.isBlank() || horaSeleccionada.isBlank(): asigna mensajeAviso = 'Por favor, selecciona una fecha y una hora antes de continuar' y NO llames a onConfirmar.*
  > *- Si ambos tienen valor: asigna mensajeAviso = '¡Cita reservada con éxito!', crea la nueva Cita y ejecuta onConfirmar(nuevaCita).*
  > *5. En la parte inferior del Box, si mensajeAviso != null, dibuja un Surface flotante alineado con Alignment.BottomCenter con fondo Color(0xFF212121), RoundedCornerShape(8.dp) y texto blanco con el mensaje de aviso.*
  > *Dame el código completo de la función AgendarCitaScreen."*
* **Respuesta técnica de la IA:**  
  La IA configuró el inicio de los selectores en cadena vacía y condicionó la invocación del callback `onConfirmar`. Incorporó el componente `Surface` anclado al fondo para notificar al usuario sobre el estado de la validación.
* **Ajuste aplicado:**  
  Se añadió el borrado automático del aviso de error en cuanto el usuario presiona cualquier chip de fecha u hora.

---

## VII. Observaciones y conclusiones

### Observaciones
1. **Recomposición y reactividad con `mutableStateListOf`:**  
   Al operar sobre una lista declarada con `mutableStateListOf` en el contenedor raíz `ClinicaApp`, las eliminaciones ejecutadas dentro del diálogo modal de `CitasScreen` se propagaron instantáneamente por el árbol de componentes sin necesidad de reiniciar la actividad ni forzar la navegación.
2. **Concurrencia de filtros en `remember`:**  
   Vincular explícitamente tanto `especialidadSeleccionada` como `textoBusqueda` en los parámetros clave del `remember` garantizó que la búsqueda en tiempo real no ignore el chip de especialidad que el usuario ya tenía activo, evitando inconsistencias visuales en el catálogo.

### Conclusiones
1. **Desarrollo manual (Fase 1) vs. Asistido por IA (Fase 2):**  
   Durante la Fase 1 se consolidó la arquitectura estructural base (pantallas secuenciales, paso de identificadores y menús desplegables con drawer). En la Fase 2, el asistente de IA aceleró significativamente el control de validaciones, la integración de cuadros modales y el filtrado dinámico, elevando la usabilidad general de la aplicación.
2. **Importancia del prompting contextual:**  
   Especificar de forma cerrada los nombres de los modelos (`Medico`, `Cita`), las firmas de los callbacks y los códigos hexadecimales institucionales evitó código deprecado y permitió que los componentes generados encajaran sin requerir refactorizaciones complejas.