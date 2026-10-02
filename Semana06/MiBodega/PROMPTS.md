# Registro de Prompts y Desarrollo Asistido por IA - Mi Bodega

* **Estudiante:** Farid Chávez
* **Curso:** Desarrollo de Aplicaciones Móviles
* **Rama de trabajo:** `mejora-ia`
* **Herramienta de IA:** Asistente Gemini (Android Studio)

---

## I. Diagnóstico y Alcance de la Mejora (Fase 2)
* **Objetivo obligatorio:** Implementar en `InicioScreen.kt` un campo de búsqueda en tiempo real que filtre la lista de productos conforme el usuario escribe, combinándose sinérgicamente con el selector horizontal de categorías (`LazyRow`), de manera que ambos filtros funcionen juntos y ninguno anule al otro.
* **Refinamientos adicionales incorporados:**
    1. Conteo dinámico y reactivo de productos dentro de cada chip de categoría (`ChipCategoriaConConteo`).
    2. Botón de limpieza rápida de texto (`trailingIcon` con `Icons.Default.Clear`).
    3. Estado amigable de búsqueda sin resultados con botón interactivo de restablecimiento ("Ver todo el catálogo").

---

## II. Bitácora de Prompts y Soluciones Técnicas

### Prompt 1: Manejo y persistencia de estado para el buscador
* **Consulta:**
  > *"Estoy desarrollando la pantalla InicioScreen en Jetpack Compose para una app de bodega. Necesito agregar un campo de texto con OutlinedTextField que funcione como buscador de productos en tiempo real mientras el usuario escribe. ¿Cómo debo declarar y manejar el estado de búsqueda para que se actualice con cada letra ingresada?"*
* **Respuesta técnica de la IA:** Propuso declarar `var textoBusqueda by remember { mutableStateOf("") }` enlazado bidireccionalmente a la propiedad `onValueChange` del `OutlinedTextField`.

---

### Prompt 2: Filtro combinado en tiempo real (Mejora Obligatoria)
* **Consulta:**
  > *"Actúa como desarrollador senior de Android con Jetpack Compose. Tengo mi pantalla InicioScreen.kt en una app de bodega que ya cuenta con un filtro por categorías (LazyRow). Necesito implementar la mejora obligatoria de mi proyecto: haz que el campo de búsqueda (OutlinedTextField) filtre la lista de productos en tiempo real a medida que el usuario escribe, combinándose correctamente con el filtro de categoría existente (ambos filtros deben funcionar juntos, no reemplazarse). La búsqueda debe evaluar coincidencias en el nombre o descripción del producto ignorando mayúsculas, minúsculas y espacios. Proporcióname la lógica reactiva con remember y cómo aplicarla en InicioScreen.kt."*
* **Respuesta técnica de la IA:** Diseñó el bloque derivado `remember(productos, categoriaSeleccionada, textoBusqueda)` aplicando un `.filter` con condiciones lógicas conjuntas (`coincideCategoria && coincideTexto`).

---

### Prompt 3: Botón de limpieza rápida y retroalimentación inicial de vacío
* **Consulta:**
  > *"Ahora que la búsqueda y el filtro de categorías funcionan combinados en InicioScreen.kt, quiero mejorar la experiencia de usuario (UX):*  
  > *1. Agrega al OutlinedTextField un botón para limpiar el texto (trailingIcon con Icons.Default.Clear) que aparezca únicamente cuando el usuario haya escrito algo y que al pulsarlo restablezca el campo a vacío.*  
  > *2. Implementa un estado vacío amigable: si la búsqueda combinada no encuentra coincidencias, muestra un mensaje centrado con el ícono Icons.Default.SearchOff, indicando que no se encontraron productos para ese texto y sugiriendo cambiar de término o categoría."*
* **Respuesta técnica de la IA:** Propuso la estructura condicional de `trailingIcon` y el layout visual de `SearchOff`.
* **Nota de integración:** Este cambio se validó contra la rama local; al comprobar que la pantalla ya contaba con una implementación previa funcional de estos elementos, se continuó hacia mejoras adicionales de mayor profundidad.

---

### Prompt 4: Indicador dinámico y conteo reactivo por categoría
* **Consulta:**
  > *"En mi pantalla InicioScreen.kt de Jetpack Compose, quiero mejorar la experiencia de navegación por categorías mientras se busca:*  
  > *1. Modifica la lista horizontal de categorías para que cada chip muestre la cantidad de productos coincidentes disponibles (por ejemplo: 'Bebidas (2)').*  
  > *2. Haz que si el usuario escribe un término en el buscador, esos números se recalculen dinámicamente según la búsqueda activa.*  
  > *Proporcióname el código actualizado para el componente ChipCategoria y su invocación en el LazyRow."*
* **Respuesta técnica de la IA:** Desarrolló el componente `ChipCategoriaConConteo`, integrando una función `count` reactiva dentro del `LazyRow` dependiente del `textoBusqueda`.

---

### Prompt 5: Acción rápida en estado vacío para restablecer catálogo
* **Consulta:**
  > *"En InicioScreen.kt, cuando la búsqueda combinada no arroja resultados (productosEnPares.isEmpty()), quiero que la tarjeta de estado vacío incluya un botón de acción principal (Button con icono de recarga o flecha) que diga 'Ver todo el catálogo'. Al presionarlo, debe limpiar el campo de texto y restablecer la categoría a 'Todos' para que el usuario recupere la lista completa con un solo toque."*
* **Respuesta técnica de la IA:** Creó un botón primario interactivo con el estilo `VerdeBodega` que restablece al mismo tiempo `textoBusqueda = ""` y `categoriaSeleccionada = "Todos"`.

---

## VII. Observaciones y Conclusiones

### Observaciones
1. **Gestión de archivos locales y sincronización de ramas con Git:**  
   Durante el proceso de merge de la rama `main` hacia `mejora-ia`, surgieron conflictos con los archivos autogenerados del entorno dentro del directorio `.idea/` (`misc.xml`, `gradle.xml`). Se identificó la importancia de mantener un `.gitignore` riguroso para herramientas de compilación de Android Studio antes de bifurcar ramas de trabajo, resolviendo la inconsistencia mediante la limpieza de temporales antes de concluir la fusión.
2. **Ciclo de recomposición y mutabilidad de estados en Jetpack Compose:**  
   Al combinar dos filtros simultáneos (texto libre y selección discreta de chips), fue indispensable vincular ambos estados en la lista de dependencias (`keys`) del `remember`. Si solo se observaba el texto, el cambio de categoría no refrescaba la grilla de productos de forma inmediata, lo cual evidenció la necesidad de diseñar estados derivados puros y sin efectos secundarios en Compose.

### Conclusiones
1. **Ventajas y desafíos de trabajar sobre un esqueleto preexistente:**  
   Iniciar el desarrollo a partir de una plantilla o esqueleto arquitectónico reduce considerablemente la fricción en la configuración inicial (rutas de navegación, paletas tipográficas y modelos de datos). Sin embargo, exige una lectura minuciosa del flujo de datos existente para evitar inconsistencias; por ejemplo, al desacoplar componentes hardcodeados para convertirlos en lambdas reutilizables en `ClienteApp.kt`.
2. **Comparativa entre el desarrollo manual (Fase 1) y el asistido por IA (Fase 2):**  
   En la Fase 1, el esfuerzo principal se concentró en la construcción de layouts visuales (`LazyColumn`, `TopAppBar`, diálogos modales) y la corrección de errores de enlace de recursos gráficos. En la Fase 2, el trabajo asistido por IA aceleró la resolución de algoritmos de filtrado reactivo y la formulación de patrones de UI avanzados (conteo dinámico y manejo de estados vacíos), permitiendo iterar soluciones de calidad comercial en un menor periodo de tiempo mediante prompting iterativo y validación técnica continua.