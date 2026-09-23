# Registro de Prompts - Fase 2 (Mejora con IA)

## Proyecto: TecsupFit
**Estudiante:** Farid Chávez

---

### Prompt 1: Filtro de búsqueda dinámico en tiempo real
* **Lo que le pedí a la IA:**
  "Necesito agregar un filtro de búsqueda dinámico en la pantalla `HomeScreen.kt`. Agrega un `OutlinedTextField` en la parte superior para que el usuario pueda filtrar la lista de clases en tiempo real por el nombre de la clase o la sala. Retóralo para que se adapte al diseño de Material3 y mantenga los colores institucionales `#00695C`."
* **Lo que tuve que corregir:**
  Ajustar la lambda del filtro para evaluar en minúsculas con `ignoreCase = true` tanto sobre el nombre de la clase como sobre la sala/horario en la colección `LazyColumn`.

---

### Prompt 2: Interactividad en botones de periodo ("Hoy" y "Esta semana")
* **Lo que le pedí a la IA:**
  "ahora un commit mas y que arregle lo de esta semana se puede ver al hacer click, porque no interactua esa opcion"
* **Lo que tuve que corregir:**
  Crear listas diferenciadas (`clasesHoy` y `clasesSemana`) y vincular el estado `filtroTiempo` para que alterne dinámicamente el catálogo mostrado y cambie los colores del botón activo al verde institucional.

---

### Prompt 3: Confirmación de reserva mediante AlertDialog
* **Lo que le pedí a la IA:**
  "Agrega un diálogo de confirmación `AlertDialog` en `HomeScreen.kt` cuando el usuario haga clic en cualquier clase de la lista. El diálogo debe mostrar el nombre de la clase, el horario y la sala, con dos botones: 'Cancelar' y 'Confirmar reserva'. Al hacer clic en 'Confirmar reserva', debe mostrar un `Toast` indicando que la clase fue reservada con éxito."
* **Lo que tuve que corregir:**
  Obtener la instancia de `LocalContext.current` para el despliegue del `Toast` y manejar la visibilidad del modal mediante una variable nullable `claseSeleccionadaDialogo`.