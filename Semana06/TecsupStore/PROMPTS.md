# Registro de Prompts - Fase 2: Mejora con IA (rama mejora-ia)

## Contexto del Proyecto
- **Proyecto:** TecsupStore
- **Tecnologías:** Kotlin, Jetpack Compose, Material 3, Git
- **Herramienta de IA:** Gemini en Android Studio
- **Objetivo:** Implementar un badge reactivo con contador numérico en la opción "Favoritos" del Navigation Drawer, conectado al estado de selección que se gestiona desde el DropdownMenu de cada tarjeta de producto.

---

## Commit 1: Soporte de Badge en el Drawer
- **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/components/AppDrawer.kt`
- **Mensaje de commit:** `feat(drawer): agregar soporte de badge con contador en el item de Favoritos`

### Prompt utilizado:
> Tengo este archivo `AppDrawer.kt` con `ModalDrawerSheet` y `NavigationDrawerItem` en Jetpack Compose Material 3. Necesito agregar un parámetro opcional `cantidadFavoritos: Int = 0` a la función `AppDrawerContent`. Además, en el ítem de navegación de 'Favoritos', muestra un componente `Badge` de color morado con el número de favoritos únicamente si `cantidadFavoritos > 0`. Dame el código completo actualizado del archivo.

### Resultado obtenido:
La IA incorporó el parámetro `cantidadFavoritos: Int = 0` en la firma de `AppDrawerContent` y utilizó el slot `badge` de `NavigationDrawerItem` con una condición `if (item == DestinoDrawer.Favoritos && cantidadFavoritos > 0)` para renderizar el componente `Badge` con fondo `Color(0xFF5E2B88)` y texto blanco.

---

## Commit 2: Lógica reactiva de Favoritos en MainActivity
- **Archivo modificado:** `app/src/main/java/com/faridchavez/tecsupstore/MainActivity.kt`
- **Mensaje de commit:** `feat(products): conectar estado reactivo de favoritos con el badge del drawer`

### Prompt utilizado:
> Tengo abierto `MainActivity.kt`. Necesito que `AppDrawerContent` reciba dinámicamente en su parámetro `cantidadFavoritos` el número de productos que tengan `esFavorito == true`. Además, cuando el usuario haga clic en la opción 'Favoritos' del DropdownMenu de cada producto, debe alternar el estado `esFavorito` de ese producto en la lista `productos` para que el contador del drawer se actualice automáticamente en tiempo real. Dame el código completo actualizado de `MainActivity.kt`.

### Resultado obtenido:
La IA calculó dinámicamente el total mediante `val totalFavoritos = productos.count { it.esFavorito }` y vinculó el evento `onClick` del ítem "Favoritos" en el `DropdownMenu` para alternar la propiedad reactiva `esFavorito`. Esto actualizó de inmediato la insignia en el Drawer al abrirlo.

VII. Observaciones y conclusiones

### Observaciones
1. **Manejo de estado en Jetpack Compose:** Al modificar una propiedad interna de un objeto dentro de una lista observable (`SnapshotStateList`), es fundamental que Compose detecte la mutación para disparar la recomposición. Asegurar que la lista o el modelo notifique el cambio fue clave para que el contador del drawer se actualice inmediatamente sin requerir navegación adicional.
2. **Uso de slots en Material 3:** El componente `NavigationDrawerItem` ya cuenta de forma nativa con el parámetro `badge = { ... }`, por lo que no fue necesario crear composables flotantes ni estructuras complejas con `Box`, manteniendo el código limpio y apegado a las guías de Material Design 3.

### Conclusiones
1. **Comparación del flujo de desarrollo (Fase 1 vs. Fase 2):** Mientras que en la Fase 1 la construcción de la interfaz y la maquetación se realizó de forma manual paso a paso, en la Fase 2 el uso del asistente de IA (Gemini) aceleró notablemente la resolución de la reactividad y la integración entre componentes dispersos (`DropdownMenu` y `NavigationDrawer`), reduciendo el tiempo de prueba y depuración.
2. **Importancia del prompting contextual:** Proporcionar a la IA el código base y un prompt específico con nombres exactos de funciones y parámetros evitó errores comunes de incompatibilidad de versiones o código deprecado, logrando una implementación precisa y limpia en pocos intentos.