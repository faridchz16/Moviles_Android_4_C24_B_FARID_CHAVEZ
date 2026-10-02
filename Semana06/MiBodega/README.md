# Laboratorio 06: Navegación y Flujo de Compra en Jetpack Compose

## 1. Descripción del Proyecto

Implementación del flujo completo de compra y catálogo para la aplicación **Mi Bodega** utilizando Jetpack Compose y componentes de Material 3. Se incorporó una arquitectura de navegación con `NavHost`, gestión reactiva de un carrito de compras en memoria con persistencia de estado por recomposición, búsqueda en tiempo real combinada con filtros por categoría y seguimiento de pedidos interactivo.

---

## 2. Componentes Implementados (Fase 1)

* **Autenticación e Inicio de Sesión Modal (`BienvenidaScreen.kt`):**
    * Diálogo modal interactivo (`AlertDialog`) anclado al botón "Iniciar sesión" con validación de credenciales (campos obligatorios de usuario y contraseña).
    * Manejo reactivo de estado para visualización de errores y redirección directa hacia el catálogo principal limpiando la pila con `popUpTo`.
    * Integración del logotipo oficial de la bodega (`ilustracion_bodega.jpg`) renderizado mediante `Image` y `painterResource`.

* **Búsqueda Reactiva y Filtrado por Categorías (`InicioScreen.kt`):**
    * Barra de búsqueda interactiva (`OutlinedTextField`) con ícono de borrado rápido (`Clear`) que filtra en tiempo real por coincidencia en nombre o descripción del producto.
    * Selector horizontal de categorías (`LazyRow`) que opera en simultáneo con el buscador de texto mediante `remember(productos, categoriaSeleccionada, textoBusqueda)`.
    * Pantalla de retroalimentación amigable (`SearchOff`) en caso de no hallar productos coincidentes.

* **Barra de Navegación Inferior y Pestañas (`NavigationBar`):**
    * Estructura fija de navegación con 4 destinos principales: Inicio, Categorías, Pedidos y Perfil.
    * Pestaña de Categorías interactiva con tarjetas por rubro que al seleccionarse filtran y redirigen automáticamente al catálogo.
    * Pestaña de Mis Pedidos con lógica condicional: estado inicial vacío que pasa a mostrar dinámicamente la tarjeta de seguimiento del **Pedido #1024 (En camino)** al confirmar una compra.
    * Vista de Perfil con datos de entrega esenciales y botón de cierre de sesión seguro que resetea el carrito y el estado de la orden.

* **Flujo de Checkout y Carrito de Compras (`CarritoScreen.kt`, `DatosEntregaScreen.kt`, `ConfirmacionScreen.kt`):**
    * Carrito de compras reactivo con carga de miniaturas fotográficas de productos (`arroz_costeno.png`, `leche_gloria.png`, `coca_cola.png`, etc.) y controles de incremento, decremento y eliminación.
    * Cálculo dinámico automático de subtotal, costo fijo de delivery (S/ 4.00) y total a pagar.
    * Formulario de entrega con validación de datos y selección de método de pago (Efectivo, Yape, Plin) mediante `RadioButton`.
    * Pantalla de confirmación con detalle del pedido y botón "Ver estado del pedido" sincronizado para abrir directamente la pestaña de Pedidos.

---

## 3. Historial de Commits (Fase 1)

1. `feat: implementar dialogo interactivo de login y cierre de sesion desde perfil`
2. `style: alinear pantallas de entrega y confirmacion a los mockups y completar vistas de navegacion`
3. `style: integrar recursos graficos de productos y logo oficial en todas las vistas`
4. `fix: sincronizar navegacion del carrito para retornar a la pestana inicial del catalogo`

---

## 4. Respuestas a las Preguntas de Reflexión

* **¿Por qué `Producto.kt` y `MainActivity.kt` se entregaron completos, y las pantallas no? ¿Qué tienen en común los archivos que sí se dejaron como esqueleto?**
    * `Producto.kt` define el modelo inmutable de datos (el contrato básico con ID, precio, nombre, categoría y recurso gráfico) necesario para que toda la aplicación hable el mismo idioma. `MainActivity.kt` es el contenedor de ciclo de vida de Android que solo hospeda a `ClienteApp()` mediante `setContent`.
    * Los archivos que se entregaron como esqueleto comparten que todos son **pantallas de interfaz de usuario (Composables)**. Su propósito era que el alumno desarrolle la lógica de presentación, la jerarquía visual con Material 3 y la emisión de eventos hacia arriba mediante callbacks.

* **¿Cómo lograste que el filtro de categoría (`LazyRow`) y el cálculo del carrito reaccionen automáticamente sin que tú "actualices" nada a mano?**
    * Gracias al modelo declarativo de Jetpack Compose: el estado del filtro (`categoriaSeleccionada` y `textoBusqueda`) se almacena en variables `mutableStateOf`. La lista visible se deriva usando `remember(productos, categoriaSeleccionada, textoBusqueda)`. Al cambiar el estado, Compose dispara automáticamente la recomposición del árbol de vistas sin manipular la UI de forma imperativa.
    * En el carrito ocurre lo mismo: `subtotal` y `total` son expresiones derivadas directamente de la lista `carrito`. Cada cambio en la lista emite una nueva instancia inmutable que recalcula las operaciones financieras y actualiza los badges numéricos de forma instantánea.

* **¿Qué diferencia notaste entre `navigate()` normal (`Inicio` → `Detalle`) y el que usa `popUpTo` (`Datos de entrega` → `Confirmación`)?**
    * `navigate()` convencional apila el nuevo destino en la cima del `BackStack`, permitiendo al usuario presionar "Atrás" para regresar a la pantalla anterior sin perder su estado previo.
    * El uso de `popUpTo` con `inclusive = true` retira destinos intermedios de la pila de navegación. Se utiliza en flujos de compra para purgar el formulario de datos y el carrito ya pagado, impidiendo que el usuario vuelva hacia atrás a una transacción ya procesada y evitando pedidos duplicados.

* **¿Qué tuviste que corregir del código que te generó la IA para el buscador en tiempo real?**
    * Se corrigió la lógica de filtrado para que no anulara la categoría seleccionada al buscar texto, permitiendo una búsqueda compuesta simultánea (categoría + texto por nombre o descripción).
    * Se eliminaron placeholders innecesarios con textos de ejemplo extensos para mantener un diseño limpio (`"Buscar productos..."`) y se añadió el ícono interactivo `Clear` para reiniciar la búsqueda de un toque.
    * Se manejó el caso de lista vacía mostrando un ícono amigable (`SearchOff`) cuando ningún producto coincide.

* **Compara el `NavigationDrawer` del Laboratorio 6 con el `NavigationBar` de esta tarea: ¿en qué caso usarías cada uno en un proyecto propio?**
    * **`NavigationBar` (Barra Inferior):** Se emplea cuando existen entre 3 y 5 secciones principales de uso frecuente. Es idóneo para aplicaciones de consumo masivo (B2C) como tiendas, delivery o redes sociales, donde el usuario necesita alternar rápidamente entre catálogo, carrito y pedidos con una sola mano.
    * **`NavigationDrawer` (Menú Lateral):** Se emplea cuando la aplicación cuenta con un número elevado de pantallas (más de 5 o 6) o con secciones de menor frecuencia de uso (configuración, reportes, gestión de cuentas, soporte). Lo usaría en sistemas de gestión empresarial (B2B), paneles de inventario o aplicaciones de administración técnica.