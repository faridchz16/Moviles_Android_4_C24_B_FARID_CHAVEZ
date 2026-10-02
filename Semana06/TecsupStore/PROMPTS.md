# Registro de Prompts - Fase 2: Mejora con IA (rama mejora-ia)

## Contexto del Proyecto

* **Proyecto:** TecsupStore
* **Tecnologías:** Kotlin, Jetpack Compose, Material 3, Git
* **Herramienta de IA:** Gemini en Android Studio
* **Objetivo:** Implementar un badge reactivo con contador numérico en la opción "Favoritos" del Navigation Drawer conectado al estado de selección del producto, y modernizar la interfaz del Drawer con íconos descriptivos de Material Design, cabecera de usuario destacada y acciones separadas.

---

### Commit 1: Soporte de Badge en el Drawer

* **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/components/AppDrawer.kt`
* **Mensaje de commit:** `feat(drawer): agregar soporte de badge con contador en el item de Favoritos`
* **Prompt utilizado:**
  > *"Tengo este archivo AppDrawer.kt con ModalDrawerSheet y NavigationDrawerItem en Jetpack Compose Material 3. Necesito agregar un parámetro opcional cantidadFavoritos: Int = 0 a la función AppDrawerContent. Además, en el ítem de navegación de 'Favoritos', muestra un componente Badge de color morado con el número de favoritos únicamente si cantidadFavoritos > 0. Dame el código completo actualizado del archivo."*
* **Resultado obtenido:**  
  La IA incorporó el parámetro `cantidadFavoritos: Int = 0` en la firma de `AppDrawerContent` y utilizó el slot `badge` de `NavigationDrawerItem` con una condición `if (item == DestinoDrawer.Favoritos && cantidadFavoritos > 0)` para renderizar el componente `Badge` con fondo `Color(0xFF5E2B88)` y texto blanco.

---

### Commit 2: Lógica reactiva de Favoritos en MainActivity

* **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/MainActivity.kt`
* **Mensaje de commit:** `feat(products): conectar estado reactivo de favoritos con el badge del drawer`
* **Prompt utilizado:**
  > *"Tengo abierto MainActivity.kt. Necesito que AppDrawerContent reciba dinámicamente en su parámetro cantidadFavoritos el número de productos que tengan esFavorito == true. Además, cuando el usuario haga clic en la opción 'Favoritos' del DropdownMenu de cada producto, debe alternar el estado esFavorito de ese producto en la lista productos para que el contador del drawer se actualice automáticamente en tiempo real. Dame el código completo actualizado de MainActivity.kt."*
* **Resultado obtenido:**  
  La IA calculó dinámicamente el total mediante `val totalFavoritos = productos.count { it.esFavorito }` y vinculó el evento `onClick` del ítem "Favoritos" en el `DropdownMenu` para alternar la propiedad reactiva `esFavorito`. Esto actualizó de inmediato la insignia en el Drawer al abrirlo.

---

### Commit 3: Integración de íconos descriptivos en el Navigation Drawer

* **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/components/AppDrawer.kt`
* **Mensaje de commit:** `style(ui): integrar iconos descriptivos de Material Design en AppDrawer`
* **Prompt utilizado:**
  > *"En mi composable AppDrawerContent de Jetpack Compose (en TecsupStore), actualmente los elementos del NavigationDrawerItem muestran un círculo genérico como ícono (RadioButton o un círculo dibujado). Modifica la lista de opciones para asignar a cada ítem un ícono representativo de androidx.compose.material.icons.Icons.Default:*
  > *- Para Inicio: usa Icons.Default.Home*
  > *- Para Mis pedidos: usa Icons.Default.ShoppingBag*
  > *- Para Favoritos: usa Icons.Default.Favorite (conservando el badge de cantidad a la derecha)*
  > *- Para Perfil: usa Icons.Default.Person*
  > *- Para Cerrar sesión: usa Icons.AutoMirrored.Filled.ExitToApp o Icons.Default.ExitToApp*
  > *Proporcióname el código actualizado de AppDrawerContent y los imports necesarios."*
* **Resultado obtenido:**  
  La IA reemplazó los círculos vectoriales genéricos en el slot `icon` de cada `NavigationDrawerItem` por íconos semánticos oficiales de Material Design 3, manteniendo intacto el slot `badge` previamente programado en el ítem de Favoritos.

---

### Commit 4: Rediseño visual del Drawer Header con tarjeta temática

* **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/components/AppDrawer.kt`
* **Mensaje de commit:** `style(ui): rediseñar DrawerHeader con contenedor destacado y estilo de usuario`
* **Prompt utilizado:**
  > *"Quiero modernizar el diseño del encabezado de usuario (DrawerHeader) en mi AppDrawerContent de Jetpack Compose:*
  > *1. Coloca los datos del usuario dentro de una tarjeta (Surface o Card) con color de fondo temático (MaterialTheme.colorScheme.primaryContainer).*
  > *2. Haz que el círculo con las iniciales 'MR' tenga un borde sutil, fondo contrastante (primary) y texto blanco en negrita.*
  > *3. Al lado del avatar, muestra el nombre 'Maria Rojas' con tipografía titleMedium en negrita, su correo institucional debajo, y un pequeño chip o badge que diga 'Estudiante Tecsup' para darle mayor identidad.*
  > *Proporcióname el código del componente del header para reemplazar el actual."*
* **Resultado obtenido:**  
  Se estructuró una cabecera moderna utilizando `Surface` con bordes redondeados (`RoundedCornerShape(16.dp)`), elevación tonal, avatar con iniciales contrastantes y un chip distintivo para categorizar el rol de la usuaria.

---

### Commit 5: Anclaje inferior de Cerrar Sesión y divisor de jerarquía

* **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/components/AppDrawer.kt`
* **Mensaje de commit:** `feat(ui): anclar boton cerrar sesion al fondo con divisor y estilo de alerta`
* **Prompt utilizado:**
  > *"En mi composable AppDrawerContent de Jetpack Compose, quiero optimizar la jerarquía visual de las opciones:*
  > *1. Agrega un Spacer(modifier = Modifier.weight(1f)) después de los ítems principales (Inicio, Mis pedidos, Favoritos, Perfil) para empujar la opción 'Cerrar sesión' hasta la parte inferior de la pantalla.*
  > *2. Antes de 'Cerrar sesión', agrega una línea divisoria sutil (HorizontalDivider con padding horizontal).*
  > *3. Modifica el NavigationDrawerItem de 'Cerrar sesión' para que use NavigationDrawerItemDefaults.colors() con un color de advertencia/error (MaterialTheme.colorScheme.error tanto para el texto como para el icono), dejándolo claro como una acción destructiva de salida.*
  > *Proporcióname la estructura final del contenedor Column para AppDrawerContent."*
* **Resultado obtenido:**  
  La IA reorganizó el contenedor vertical agregando un espaciador flexible (`weight(1f)`), un divisor horizontal y estilizó la opción de cierre de sesión con colores de alerta (`MaterialTheme.colorScheme.error`), separando claramente las rutas de navegación de las acciones de sesión.

---

## VII. Observaciones y conclusiones

### Observaciones
1. **Manejo de estado en Jetpack Compose:** Al modificar una propiedad interna de un objeto dentro de una lista observable (`SnapshotStateList`), es fundamental que Compose detecte la mutación para disparar la recomposición. Asegurar que la lista o el modelo notifique el cambio fue clave para que el contador del drawer se actualice inmediatamente sin requerir navegación adicional.
2. **Uso de slots en Material 3:** El componente `NavigationDrawerItem` cuenta de forma nativa con los parámetros `icon = { ... }` y `badge = { ... }`. Esto facilitó integrar íconos vectoriales dinámicos y la insignia de favoritos sin necesidad de sobrecargar la UI con contenedores `Box` flotantes innecesarios.
3. **Distribución espacial con modificadores flexibles:** Para desacoplar la opción "Cerrar sesión" del resto de enlaces, el uso del modificador `Modifier.weight(1f)` dentro del `Column` del drawer garantizó que el botón se ancle al fondo de manera consistente en cualquier resolución de pantalla.

### Conclusiones
1. **Comparación del flujo de desarrollo (Fase 1 vs. Fase 2):** Mientras que en la Fase 1 la construcción de la interfaz y la maquetación se realizó de forma manual paso a paso, en la Fase 2 el uso del asistente de IA (Gemini) aceleró notablemente la resolución de la reactividad y el refinamiento estético de la interfaz, reduciendo los tiempos de iteración y depuración.
2. **Importancia del prompting contextual e incremental:** Proporcionar a la IA requerimientos modulares (primero lógica funcional con estados y badges, luego diseño de cabecera y finalmente jerarquía de salida) evitó regresiones de código y permitió construir un historial de commits limpio, descriptivo y trazable en la rama `mejora-ia`.