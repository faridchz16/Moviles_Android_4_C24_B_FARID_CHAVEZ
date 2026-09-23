# TECSUP Fit
App de reserva de clases de gimnasio — Tarea integradora Semanas 1 a 6 (sin MVVM).  
Curso: Programación en Móviles — Docente: Juan León S.  
Alumno: Farid Chavez / Jordan Abad

---

## Rubro elegido

**Opción B — TECSUP Fit** (Reserva de clases de gimnasio, navegación secundaria con BottomBar)

---

## Requisitos funcionales implementados

1. **Inicio (`HomeScreen`):** `LazyRow` con chips de filtro (`Hoy`, `Esta semana`) de selección única y `LazyColumn` con la lista de clases disponibles (mínimo 3 clases de ejemplo), donde cada tarjeta muestra el nombre, horario y sala correspondiente.

2. **Detalle de clase (`DetalleClaseScreen`):** Recibe la clase elegida por parámetro de navegación (`claseId: String`), muestra la descripción detallada, disponibilidad de cupos y el botón **"Reservar cupo"**.

3. **Confirmación (`ConfirmacionScreen`):** Resumen de la reserva realizada (nombre de la clase, fecha u horario y sala) con un botón para navegar a **"Ver mis reservas"**.

4. **Navegación inferior / BottomBar (`BottomBar`):** Visible en Inicio, Mis reservas, Rutinas y Perfil; con 4 pestañas fijas donde el ícono activo se resalta según la pantalla actual.

---

## Otros componentes del sistema

- **Mis reservas (`ReservasScreen`):** `LazyColumn` con las clases reservadas, cada una con su estado (*Confirmada* / *Completada*) diferenciado visualmente por color.
- **Perfil (`PerfilScreen`):** Datos del usuario y estadísticas simples (total de clases tomadas y racha de asistencia).

---

## Preguntas para la sustentación

### 1. ¿Cómo llega el ítem elegido en Inicio (clase) hasta la pantalla de confirmación? Describe la ruta completa del dato.
> En `HomeScreen.kt`, al hacer clic sobre una tarjeta de la `LazyColumn`, se extrae el ID de la clase (`claseId`) y se envía como parámetro en la ruta de navegación del `NavController` hacia `DetalleClaseScreen`. En esta pantalla, al presionar el botón **"Reservar cupo"**, el dato del ID se vuelve a pasar a la ruta de `ConfirmacionScreen`, la cual consulta los datos de la clase desde el repositorio/lista en memoria para mostrar el resumen final.

### 2. ¿Cómo sabe el `bottomBar` cuál ícono resaltar en cada pantalla?
> La `BottomBar` obtiene el destino actual del flujo de navegación utilizando la función `navController.currentBackStackEntryAsState()`. Al comparar la ruta activa (`currentDestination?.route`) con el atributo `.ruta` de cada `Destino`, Compose asigna `selected = true` únicamente al ítem coincidente, cambiando su color a verde pino (`#005B41`).

### 3. ¿Por qué la selección de fecha/hora (o de horario) se comporta como un `RadioButton`, aunque visualmente sean "chips"?
> Porque la lógica de selección maneja una sola variable de estado mediante `remember { mutableStateOf(...) }`. Al seleccionar un chip, esta variable toma un único valor a la vez, garantizando un comportamiento de **opción única** (mutuamente excluyente), exactamente como el de un grupo de `RadioButton`.