# Registro de Prompts - Fase 2 (Mejora con IA)

## Proyecto: TecsupFit
**Estudiante:** Farid Chávez

---

### Prompt 1: Implementación de la barra de navegación y estructura general
* **Lo que le pedí a la IA:**
  "Agrega la barra de navegación BottomBar a MainActivity con las pestañas Inicio, Reservas, Rutinas y Perfil."
* **Lo que tuve que corregir:**
  Corregir nombres de imports, actualizar el tipo de dato de clase a `ClaseFit` y asociar la variable `onIrAMisReservas` que no coincidía en el listener de confirmación.

---

### Prompt 2: Creación de la pantalla de Rutinas
* **Lo que le pedí a la IA:**
  "Crea la pantalla RutinasScreen con una lista de ejercicios recomendados para que no quede vacía la pestaña."
* **Lo que tuve que corregir:**
  Ajustar el color primario a verde institucional `#00695C` e importar correctamente los componentes de Compose Material3.

---

### Prompt 3: Cancelación de reservas con AlertDialog
* **Lo que le pedí a la IA:**
  "Agrega la funcionalidad de cancelar una reserva en ReservasScreen mostrando un AlertDialog de confirmación."
* **Lo que tuve que corregir:**
  Manejar el estado mutable de la lista de reservas para refrescar la interfaz en tiempo real al marcar la reserva como 'Cancelada' en color rojo.