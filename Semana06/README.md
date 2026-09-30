# Laboratorio 06: Menús y Navegación en Jetpack Compose

## 1. Descripción del Proyecto
Implementación de patrones de navegación jerárquica y menús contextuales en la aplicación **TECSUP Store** utilizando **Jetpack Compose** y componentes de **Material 3**[cite: 2, 9]. Se incorporó un menú emergente contextual (`DropdownMenu`) en cada elemento de la lista y un panel lateral deslizable (`ModalNavigationDrawer`) como estructura de navegación principal[cite: 2, 9].

---

## 2. Componentes Implementados (Fase 1)

* **Menú Contextual (`DropdownMenu`)**:[cite: 2]
  * Integrado dentro de `TarjetaProducto.kt` anclado a un contenedor `Box` junto al activador `IconButton` (`Icons.Default.MoreVert`)[cite: 2, 3].
  * Opciones personalizadas con íconos vectoriales (`leadingIcon`) y separadores (`HorizontalDivider`): Favoritos, Compartir y Reportar[cite: 2, 3].
  * Manejo reactivo de estado para alternar la selección de favoritos (`onToggleFavorito`)[cite: 3].

* **Navegación Lateral (`ModalNavigationDrawer`)**:[cite: 2, 3]
  * Componente `AppDrawerContent` basado en `ModalDrawerSheet`[cite: 2, 3].
  * Encabezado institucional con avatar circular ("MR"), nombre de usuario y correo corporativo (`maria@tecsup.edu.pe`)[cite: 9].
  * Opciones de navegación (`NavigationDrawerItem`) con soporte de selección activa (`selected`), paleta institucional (`#5E2B88`) y cierre automático controlado mediante corrutinas (`rememberCoroutineScope`)[cite: 2, 3].

---

## 3. Historial de Commits (Fase 1)

1. `feat(ui): agregar icono de 3 puntos y estado expanded en la tarjeta de producto`[cite: 2]
2. `feat(ui): DropdownMenu con opciones basicas funcionando`[cite: 2]
3. `style(ui): personalizacion del DropdownMenu (iconos, divisores)`[cite: 2]
4. `feat(drawer): estructura del NavigationDrawer con ModalDrawerSheet`[cite: 2]
5. `feat(navigation): navegacion real desde los items del drawer`[cite: 2]
6. `style(drawer): personalizacion del drawer (header con avatar, opcion activa)`[cite: 2]

---

## 4. Respuestas a las Preguntas de Reflexión

### ¿Por qué el `DropdownMenu` se declara dentro de un `Box` junto al ícono que lo activa, y no en cualquier parte de la pantalla?
En Jetpack Compose, el componente `DropdownMenu` utiliza internamente un `Popup` cuyas coordenadas relativas de posicionamiento dependen del nodo padre directo en el árbol de composición. Al envolver el botón activador (`IconButton`) y el `DropdownMenu` dentro del mismo contenedor `Box`, el menú toma las coordenadas y dimensiones de ese `Box` como punto de anclaje (*anchor*). Si se declarase en la raíz o fuera de la tarjeta, el menú perdería su referencia contextual en la pantalla y se renderizaría desalineado respecto al ícono que el usuario presionó.

### ¿Qué diferencia de alcance hay entre las opciones del `DropdownMenu` (afectan solo a un producto) y las del `NavigationDrawer` (afectan a toda la app)?
La diferencia radica en el **alcance del estado y la responsabilidad arquitectónica**:
* **`DropdownMenu` (Alcance local/contextual):** Opera sobre la instancia individual de la tarjeta donde fue accionado. Modifica o consulta propiedades directas de ese único objeto `Producto` (por ejemplo, cambiar su atributo booleano `esFavorito`), sin afectar el flujo estructural de la interfaz general.
* **`NavigationDrawer` (Alcance global/estructural):** Actúa como el nivel superior de control de navegación de la aplicación (*App-level navigation*). Sus acciones mutan el estado raíz de la pantalla (`rutaActual` en el `Scaffold`), determinando qué vista, flujo o módulo completo se proyecta en el contenedor principal.

### ¿Cómo tuviste que estructurar tu código para que el contador de favoritos del drawer "se entere" de lo que pasa en el DropdownMenu de cada producto?
Se aplicó el patrón arquitectónico de **Elevación de Estado (*State Hoisting*)**:
1. La lista reactiva de productos no se almacenó de forma aislada dentro de cada tarjeta, sino en el nivel superior del árbol composable (`MainActivity` / contenedor padre) mediante `remember { mutableStateListOf(...) }`.
2. Las tarjetas no mutan el estado directamente; en su lugar, exponen un evento callback hacia arriba (`onToggleFavorito: (Producto) -> Unit`).
3. Cuando el usuario interactúa con la opción del `DropdownMenu`, el evento llega al padre, donde se actualiza el estado de la lista.
4. El Drawer recibe como parámetro de entrada el valor derivado (un contador calculado con `productos.count { it.esFavorito }`). Al ser un estado observable, Jetpack Compose dispara automáticamente la recomposición del Drawer y de su badge numérico en cuanto cambia cualquier elemento de la lista.

### ¿Qué tuviste que corregir del código que te generó la IA para la mejora del badge de favoritos?
Las correcciones técnicas clave fueron:
1. **Inmutabilidad y Recomposición:** La IA sugería mutar la propiedad directamente en el objeto (`producto.esFavorito = true`), lo cual no dispara la recomposición automática en Compose. Se corrigió creando copias inmutables del objeto (`producto.copy(esFavorito = !producto.esFavorito)`) y actualizando el índice correspondiente dentro de la lista observable.
2. **Componentes deprecados / Imports de Material 3:** Se corrigió el uso de divisores y badges heredados de Material 2, estandarizando hacia `HorizontalDivider` y el componente `Badge` con `BadgedBox` nativos de `androidx.compose.material3`.
3. **Mapeo de Rutas y Scope:** Se aseguró el manejo del estado del drawer encapsulado dentro de un `rememberCoroutineScope` para invocar `drawerState.close()` de manera segura sin bloquear el hilo principal de la UI.
