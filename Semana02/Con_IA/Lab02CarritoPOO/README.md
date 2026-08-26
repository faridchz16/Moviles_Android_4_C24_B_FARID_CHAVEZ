# Laboratorio 02 - Carrito de Compras con POO en Kotlin

## Estudiante

Farid Chavez

## Descripción del proyecto

En este proyecto se desarrolló un carrito de compras utilizando Kotlin y aplicando conceptos de Programación Orientada a Objetos (POO).

El programa permite registrar diferentes tipos de productos, calcular sus importes, obtener el subtotal, calcular el IGV del 18%, obtener el total de la compra, aplicar descuentos según el monto y determinar cuál es el producto más caro.

A diferencia de la versión realizada sin IA, en esta versión se organizaron las funcionalidades mediante clases, objetos, herencia y polimorfismo.

---

## Objetivo

Aplicar los principales conceptos de Programación Orientada a Objetos en Kotlin mediante el desarrollo de un carrito de compras, utilizando clases relacionadas entre sí y aplicando polimorfismo para que diferentes tipos de productos puedan tener comportamientos distintos.

---

## Programación Orientada a Objetos aplicada

Para desarrollar el programa se utilizaron diferentes clases que permiten organizar mejor las responsabilidades del sistema.

Las principales clases utilizadas fueron:

- `Producto`
- `Electronico`
- `Accesorio`
- `Carrito`

Además, se utilizó el archivo `Main.kt` para crear los objetos, agregarlos al carrito y ejecutar el programa.

---

## Clase Producto

`Producto` es la clase padre del programa.

Contiene los datos principales de cada producto:

- Nombre.
- Precio.
- Cantidad.

También contiene el método:

`calcularImporte()`

Este método calcula inicialmente el precio del producto multiplicado por su cantidad.

La clase fue declarada como `open` para permitir que otras clases puedan heredar de ella.

---

## Herencia

Para aplicar herencia se crearon las clases:

### Electronico

La clase `Electronico` hereda de `Producto`.

Esta clase sobrescribe el método `calcularImporte()` y aplica un 5% adicional al importe del producto.

### Accesorio

La clase `Accesorio` también hereda de `Producto`.

En este caso, el importe se calcula normalmente multiplicando el precio por la cantidad.

De esta manera, ambas clases comparten las características de `Producto`, pero pueden tener comportamientos diferentes.

---

## Polimorfismo

El polimorfismo se aplicó mediante el método:

`calcularImporte()`

Este método está definido en la clase `Producto` y es sobrescrito mediante `override` en las clases `Electronico` y `Accesorio`.

Esto permite trabajar con objetos de diferentes clases como si fueran objetos de tipo `Producto`.

Por ejemplo, el carrito puede almacenar productos electrónicos y accesorios dentro de una misma colección de productos.

Cuando se ejecuta:

`producto.calcularImporte()`

Kotlin utiliza automáticamente la implementación correspondiente según el tipo real del objeto.

Por ejemplo:

- Un `Electronico` aplica el 5% adicional.
- Un `Accesorio` realiza el cálculo normal.

Esto demuestra el uso del polimorfismo dentro del proyecto.

---

## Clase Carrito

Se creó la clase `Carrito` para administrar los productos y realizar las operaciones principales del programa.

Dentro de esta clase se implementaron los siguientes métodos:

- `agregarProducto()`
- `calcularSubtotal()`
- `calcularIGV()`
- `calcularTotal()`
- `calcularDescuento()`
- `obtenerProductoMasCaro()`
- `mostrarDetalle()`

El carrito almacena objetos de tipo `Producto`, permitiendo trabajar tanto con objetos `Electronico` como con objetos `Accesorio`.

---

## Descuento

El descuento se implementó utilizando `when`.

Las condiciones utilizadas fueron:

- Si el total supera S/ 5000, se aplica un descuento del 10%.
- Si el total supera S/ 3000, se aplica un descuento del 5%.
- En caso contrario, no se aplica descuento.

---

## Producto más caro

Para obtener el producto con el precio más alto se utilizó:

`maxByOrNull`

El programa muestra el nombre y precio del producto más caro registrado en el carrito.

---

## Resultado obtenido

En la ejecución final se utilizaron los siguientes productos:

- Laptop.
- Mouse.
- Teclado.
- Monitor.

El programa obtuvo los siguientes resultados:

- Producto más caro: Laptop.
- Precio del producto más caro: S/ 2500.00.
- Subtotal: S/ 3685.00.
- IGV (18%): S/ 663.30.
- Total: S/ 4348.30.
- Descuento: S/ 217.42.
- Total con descuento: S/ 4130.89.

---

## Prompt utilizado con IA

Estoy desarrollando un proyecto en Kotlin para el curso de Desarrollo de Aplicaciones Móviles y necesito realizar un programa de carrito de compras aplicando Programación Orientada a Objetos (POO).

Quiero que el programa sea sencillo, ordenado y adecuado para un estudiante de instituto de 4to ciclo. El proyecto debe utilizar clases, objetos, herencia y principalmente polimorfismo.

El programa debe cumplir con lo siguiente:

1. Crear una clase principal llamada `Producto` que contenga los atributos nombre, precio y cantidad.

2. Crear clases que hereden de `Producto`, como `Electronico` y `Accesorio`, para representar diferentes tipos de productos.

3. Aplicar polimorfismo mediante un método llamado `calcularImporte()`, de manera que cada tipo de producto pueda realizar su propio cálculo.

4. En la clase `Electronico`, sobrescribir `calcularImporte()` para aplicar un 5% adicional al importe del producto.

5. En la clase `Accesorio`, sobrescribir `calcularImporte()` manteniendo el cálculo normal de precio por cantidad.

6. Crear una clase `Carrito` que permita almacenar diferentes objetos de tipo `Producto`.

7. Dentro de `Carrito`, implementar métodos para agregar productos, calcular el subtotal, calcular el IGV del 18%, calcular el total, aplicar descuentos, encontrar el producto más caro y mostrar el detalle del carrito.

8. Aplicar los descuentos utilizando `when`: 10% si el total supera S/ 5000, 5% si supera S/ 3000 y sin descuento en los demás casos.

9. Utilizar una colección de tipo `Producto` que permita trabajar con objetos `Electronico` y `Accesorio`, demostrando el uso del polimorfismo.

10. En `Main.kt`, crear diferentes productos, agregarlos al carrito y mostrar el detalle, producto más caro, subtotal, IGV, total, descuento y total con descuento.

Quiero que el código esté separado en clases para que sea ordenado y fácil de entender. También necesito que se pueda identificar claramente dónde se aplican la Programación Orientada a Objetos, la herencia y el polimorfismo.
