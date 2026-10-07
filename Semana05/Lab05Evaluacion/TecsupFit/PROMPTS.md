# Bitácora de Prompts y Mejoras con Asistencia de IA — TecsupFit

**Estudiante:** Farid Chavez  
**Curso:** Desarrollo de Aplicaciones Móviles Android  
**Proyecto:** TecsupFit  
**Rama:** `mejora-ia`  
**Fecha:** Octubre 2026

---

## 1. Contexto del Proyecto

TecsupFit es una aplicación móvil desarrollada en Android con Jetpack Compose orientada a la gestión y reserva de cupos en clases de entrenamiento funcional, spinning, yoga y acondicionamiento físico.

Durante esta etapa de mejora asistida por Inteligencia Artificial, se implementaron funcionalidades clave de interactividad, validación, búsqueda reactiva y persistencia temporal del estado de reservas.

---

## 2. Registro Cronológico de Prompts

### Prompt 1: Implementación de Buscador Reactivo y Filtro Combinado
* **Objetivo:** Permitir al usuario buscar clases por texto (nombre, sala, horario) y filtrarlas mediante chips por período temporal ("Hoy" / "Esta semana").
* **Prompt enviado:**
  > *"Necesito agregar en `HomeScreen.kt` un campo `OutlinedTextField` reactivo que filtre en tiempo real la lista de clases por título, sala u horario, combinándolo con los chips de filtro por período ('Hoy' y 'Esta semana'). Si la búsqueda no arroja resultados, debe mostrar un estado visual vacío amigable con opción de limpiar el filtro."*
* **Resultado obtenido:**
    * Uso de `remember(filtroPeriodo, textoBusqueda, clases)` para cálculo reactivo sin recomputaciones innecesarias.
    * Incorporación de botón para limpiar texto en el `OutlinedTextField`.
    * Vista de `SearchOff` en caso de coincidencias vacías.

---

### Prompt 2: Pantalla de Detalle de Clase y Modal de Confirmación
* **Objetivo:** Diseñar una vista detallada para la clase seleccionada con confirmación previa mediante un diálogo modal (`AlertDialog`) antes de procesar la reserva.
* **Prompt enviado:**
  > *"Crea la pantalla `DetalleClaseScreen.kt` en Jetpack Compose que reciba el objeto `ClaseFit` seleccionado, muestre su tarjeta superior con ícono, horarios, sala y descripción detallada. Al pulsar 'Reservar cupo', debe abrir un `AlertDialog` modal preguntando si confirma la reserva y emitiendo un Toast de éxito."*
* **Resultado obtenido:**
    * Componente `DetalleClaseScreen` con navegación hacia atrás (`ArrowBack`).
    * Diálogo modal `AlertDialog` con botones de Confirmar y Cancelar.
    * Disparo de evento `Toast` informativo en el contexto de Android al confirmar.

---

### Prompt 3: State Hoisting y Sincronización Global de Reservas
* **Objetivo:** Hacer que las reservas confirmadas por el usuario se sincronicen y aparezcan inmediatamente listadas dentro de la pantalla "Mis Reservas".
* **Prompt enviado:**
  > *"Tengo un problema donde reservo una clase pero no se refleja en la pestaña 'Mis reservas'. Ayúdame a implementar State Hoisting en `MainActivity.kt` usando `remember { mutableStateListOf<ClaseFit>() }` y pasa ese estado a `ReservasScreen.kt` para que las nuevas reservas se muestren dinámicamente arriba con estado 'Confirmada'."*
* **Resultado obtenido:**
    * Elevación del estado (`misReservasGlobales`) a `MainActivity.kt`.
    * Paso de parámetros hacia `ReservasScreen(reservas = misReservasGlobales)`.
    * Renderizado reactivo mediante `LazyColumn` y tarjetas dinámicas de reserva.

---

### Prompt 4: Resolución de Conflictos de Gradle y Tipado en Jetpack Compose
* **Objetivo:** Corregir discrepancias de tipos en los identificadores de modelo (`Int` vs `String`) y resolver problemas de compilación en `LazyListScope.items`.
* **Prompt enviado:**
  > *"Tengo errores en Android Studio: 'Argument type mismatch: actual type is String, but Int was expected' en el campo id de `ClaseFit`, y conflicto con la sobrecarga de `items` en LazyColumn. Corrige la firma de `HomeScreen` y el modelo."*
* **Resultado obtenido:**
    * Normalización del campo `id` a tipo numérico entero (`Int`).
    * Import explícito de `androidx.compose.foundation.lazy.items` especificando el parámetro `items = clasesFiltradas` y su clave única `key = { it.id }`.

---

## 3. Conclusiones y Aprendizaje sobre Asistencia de IA

1. **Precisión en los requerimientos:** La especificidad al definir nombres de parámetros (por ejemplo, `onSeleccionarClase` vs `onClaseClick`) evita discrepancias de firmas entre composables padre e hijo.
2. **Arquitectura reactiva en Compose:** El uso de herramientas de IA facilita estructurar flujos limpios de *State Hoisting*, separando la lógica de estado en la raíz (`MainActivity`) de la representación puramente visual de los componentes (`HomeScreen`, `ReservasScreen`).
3. **Control de versiones atómico:** Mantener los commits organizados por responsabilidad (documentación, UI, reactividad y estado global) asegura un historial rastreable y profesional dentro del repositorio de Git.