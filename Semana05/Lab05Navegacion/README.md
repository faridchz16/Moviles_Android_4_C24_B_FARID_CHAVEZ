# Laboratorio 05 - Navegación en Jetpack Compose

## Proyecto

Lab05Navegacion

## Descripción

Aplicación Android desarrollada con Kotlin y Jetpack Compose para implementar navegación entre diferentes pantallas utilizando Navigation Compose.

El proyecto incluye las pantallas de inicio de sesión, bienvenida, directorio de alumnos, expediente académico y configuración de perfil.

## Mejoras realizadas con Gemini

Se utilizó Gemini integrado en Android Studio como apoyo para mejorar la presentación visual de la aplicación.

Las mejoras realizadas incluyen:

- Implementación de una pantalla de inicio de sesión.
- Diseño de una pantalla de bienvenida con degradado.
- Tarjetas para acceder al directorio y perfil académico.
- Directorio de alumnos con tarjetas y navegación.
- Expediente académico con información del alumno seleccionado.
- Configuración de perfil.
- Uso de colores morados y lilas basados en Material 3.
- Mejora de tipografía y jerarquía visual.
- Uso de iconos Material.
- Uso de tarjetas con bordes redondeados y sombras.
- Conservación de Navigation Compose.
- Conservación del argumento `itemId: Int` para la navegación hacia el detalle.

## Prompt utilizado con Gemini

Se utilizó un prompt para solicitar que el diseño visual de la aplicación reprodujera lo más fielmente posible la referencia proporcionada por el docente, manteniendo la navegación y funcionalidad existente.

El objetivo fue adaptar las pantallas sin modificar otros laboratorios ni realizar cambios innecesarios en la lógica del proyecto.

## Tecnologías

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Android Studio

## Navegación

Login → Home → Directorio → Expediente

Home → Configuración de Perfil

Las flechas permiten regresar a la pantalla anterior y las opciones de cerrar sesión regresan al inicio de sesión.
## PROMPT UTILIZADO CON GEMINI EN ANDROID STUDIO

Actúa como desarrollador Android especializado en Kotlin, Jetpack Compose y Material 3.

Estoy trabajando sobre el proyecto existente Lab05Navegacion. La aplicación utiliza Kotlin, Jetpack Compose y Navigation Compose. Actualmente cuenta con navegación entre las pantallas Inicio, Lista, Detalle y Mi Perfil, y la pantalla Detalle recibe un argumento itemId de tipo Int.

Quiero mejorar la presentación visual de la aplicación para que se reproduzca lo más fielmente posible al diseño de referencia proporcionado por el docente. No quiero un diseño genérico ni una propuesta diferente. Mantén una apariencia visual muy cercana a la referencia, respetando colores, degradados, tamaños relativos, tarjetas, sombras, espacios, esquinas redondeadas, iconos y jerarquía de textos.

No crees otro proyecto. Trabaja sobre el proyecto actual Lab05Navegacion y conserva la navegación existente con Navigation Compose y el argumento itemId: Int.

### PANTALLA DE INICIO / LOGIN

Crear una pantalla de inicio de sesión con:

- Fondo lila muy claro.
- Tarjeta central con esquinas redondeadas y sombra suave.
- Título "Portal Académico" en color morado.
- Subtítulo "Accede a tu cuenta".
- Campo "Correo Institucional" con icono de correo.
- Campo "Contraseña" con icono de candado.
- Opción para mostrar u ocultar la contraseña.
- Botón morado "INICIAR SESIÓN".
- Texto "¿Olvidaste tu contraseña?".

El botón de inicio de sesión debe llevar a la pantalla principal.

### PANTALLA DE BIENVENIDA / HOME

Crear una pantalla con:

- Fondo con degradado vertical desde morado oscuro en la parte superior hasta lila muy claro en la parte inferior.
- Texto "Bienvenido, Farid Chavez".
- Texto secundario "¿Qué deseas gestionar hoy?".
- Tarjeta "Directorio de Alumnos".
- Subtítulo "Consulta expedientes y registros".
- Tarjeta "Mi Perfil Académico".
- Subtítulo "Información personal y detalles".
- Iconos morados dentro de recuadros lila claro.
- Flechas de navegación a la derecha.
- Botón inferior "Cerrar Sesión Segura".

Las tarjetas deben tener esquinas redondeadas, sombra suave y separación uniforme.

### PANTALLA DIRECTORIO DE ALUMNOS

Modificar la pantalla Lista para mostrar:

- TopAppBar de color lila claro.
- Flecha oficial de regreso.
- Título "Directorio de Alumnos".
- Cinco tarjetas de alumnos.
- Cada tarjeta debe contener un avatar circular.
- Nombre del alumno.
- Carrera debajo del nombre.
- Flecha indicadora a la derecha.
- Fondo gris/lila claro.
- Esquinas redondeadas.
- Sombra suave.
- Separación uniforme entre tarjetas.

Los alumnos de ejemplo son:

1. Farid Chavez — Ingeniería de Software
2. Lucía Mendoza — Administración de Empresas
3. Carlos Quispe — Redes y Comunicaciones
4. Ana Torres — Diseño y Desarrollo de Software
5. Jorge Huamán — Ciberseguridad

Al tocar un alumno se debe abrir su pantalla de detalle enviando correctamente su ID mediante el argumento itemId: Int.

### PANTALLA EXPEDIENTE ACADÉMICO

Modificar DetailScreen para representar el expediente académico del alumno seleccionado.

Debe contener:

- Barra superior blanca.
- Flecha de regreso.
- Título "Expediente Académico".
- Encabezado morado con esquinas inferiores redondeadas.
- Avatar circular grande superpuesto sobre el encabezado.
- Nombre del alumno centrado.
- Carrera centrada.
- Tarjeta lila claro con la información académica.
- ID estudiantil.
- Correo electrónico.
- Facultad / sede.
- Biografía académica.
- Iconos morados.
- Separadores suaves.

El alumno mostrado debe depender del itemId recibido desde Navigation Compose.

### PANTALLA CONFIGURACIÓN DE PERFIL

Modificar ProfileScreen para mostrar:

- Barra superior blanca.
- Flecha de regreso.
- Título "Configuración de Perfil".
- Encabezado con degradado morado.
- Avatar circular centrado.
- Nombre "Farid Chavez".
- Sección "INFORMACIÓN PERSONAL".
- Correo institucional de ejemplo.
- Teléfono de ejemplo.
- Sección "ACADÉMICO".
- Carrera "Desarrollo de Software".
- Ciclo "IV Ciclo".
- Iconos dentro de recuadros lila claro.
- Botón inferior rosado claro "Cerrar Sesión".

No inventar datos personales reales. Los datos de correo y teléfono deben mantenerse como ejemplos reemplazables.

### DISEÑO VISUAL

Utilizar Material 3 y mantener una identidad visual basada en tonos morados y lilas.

Utilizar:

- Fondos lila muy claro.
- Morado como color principal.
- Degradados verticales.
- Tarjetas con esquinas redondeadas.
- Sombras suaves.
- Iconos oficiales de Material Design.
- Tipografía Material 3.
- titleLarge.
- titleMedium.
- bodyLarge.
- bodyMedium.
- labelLarge.

Mantener una distribución visual cercana a la referencia del docente.

### AVATARES

No utilizar imágenes externas ni fotografías de personas reales identificables.

Si no se proporcionan imágenes, utilizar avatares generados mediante iniciales dentro de círculos estilizados.

Las iniciales pueden ser:

- Farid Chavez → FC
- Lucía Mendoza → LM
- Carlos Quispe → CQ
- Ana Torres → AT
- Jorge Huamán → JH

En ListScreen y DetailScreen debe mostrarse el mismo avatar correspondiente al alumno seleccionado.

### NAVEGACIÓN

Conservar Navigation Compose.

Mantener el argumento:

itemId: Int

Las rutas deben permitir:

Login → Home → Lista → Detalle

Home → Perfil

Las flechas deben regresar a la pantalla anterior.

Cerrar sesión debe regresar al Login.

No eliminar la funcionalidad existente del proyecto.

### ARCHIVOS

Modificar solamente los archivos necesarios del proyecto actual.

Entre ellos pueden encontrarse:

- LoginScreen.kt
- HomeScreen.kt
- ListScreen.kt
- DetailScreen.kt
- ProfileScreen.kt
- AppNavigation.kt
- Screen.kt
- Color.kt
- Theme.kt
- Type.kt

No modificar otros laboratorios del repositorio.

### FORMA DE TRABAJO

Antes de modificar código, revisar la estructura actual del proyecto.

No crear otro proyecto.

No crear carpetas ConIA o SinIA nuevas.

No modificar otros laboratorios.

Mantener las funcionalidades existentes.

Si se necesita agregar una dependencia, indicar primero cuál es y por qué.

Después de cada modificación comprobar que el proyecto compile y que la navegación continúe funcionando.

El objetivo es que el resultado final sea visualmente lo más parecido posible a la referencia del docente, manteniendo la funcionalidad de navegación y el argumento itemId: Int.