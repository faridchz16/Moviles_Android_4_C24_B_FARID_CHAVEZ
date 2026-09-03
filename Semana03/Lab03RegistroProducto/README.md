# Laboratorio 03: Registro de Producto
**Estudiante:** Farid Chavez Campos  
**Curso:** Programación en Móviles

## Descripción
Aplicación móvil desarrollada con Jetpack Compose que implementa un formulario para el registro de productos con cálculo automático de importe total y persistencia temporal reactiva mediante mutableStateOf y remember.

## Capturas de Pantalla

### Pantalla Inicial
![Pantalla Inicial](captura1.png)

### Producto Registrado
![Producto Registrado](captura2.png)

## Pregunta de Reflexión: remember

### ¿Qué pasaría si declaras las variables de los campos SIN remember?
Si se declaran las variables de estado utilizando únicamente `mutableStateOf("")` sin envolverlas en `remember`, el estado se reinicializa a su valor por defecto cada vez que el composable se recompone. En consecuencia, cada vez que el usuario teclea una letra o número, Compose redibuja la interfaz, la variable vuelve a quedar vacía y el texto ingresado nunca llega a visualizarse en el campo.

## Mejora con IA

| Prompt que usé | Qué generó Gemini | Qué acepté o corregí (y por qué) |
|---|---|---|
| "Agrega validación de campos vacíos (si falta un dato al presionar AGREGAR, mostrar un mensaje de error en rojo en lugar de la Card) y un botón Limpiar que vacíe el formulario en PantallaRegistro sin alterar la estructura base." | Generó el estado `errorMensaje`, la validación condicional con `when`, el texto de error en color rojo y un `OutlinedButton` para limpiar todos los estados. | **Acepté:** La lógica de validación visual y el botón Limpiar.<br>**Corregí:** La IA dejó los campos con teclado de texto genérico, permitiendo ingresar letras en precio y cantidad. Agregué manualmente `keyboardOptions` con `KeyboardType.Decimal` y `KeyboardType.Number` para forzar el teclado numérico correcto. |