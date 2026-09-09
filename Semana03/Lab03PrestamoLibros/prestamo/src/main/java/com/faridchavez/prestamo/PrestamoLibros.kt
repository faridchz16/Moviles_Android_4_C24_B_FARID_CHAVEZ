package com.faridchavez.prestamo

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

fun main() {

    val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    formato.isLenient = false

    println("===== PRESTAMO DE LIBROS =====")
    println()

    print("Ingrese el titulo del libro: ")
    val tituloLibro = readln()

    print("Ingrese el tipo de usuario: ")
    val tipoUsuario = readln()

    print("Ingrese la fecha de prestamo (dd/MM/yyyy): ")
    val fechaPrestamo = readln()

    print("Ingrese la fecha de devolucion (dd/MM/yyyy): ")
    val fechaDevolucion = readln()

    print("Ingrese la fecha de entrega (dd/MM/yyyy): ")
    val fechaEntrega = readln()

    try {

        val devolucion = formato.parse(fechaDevolucion)
        val entrega = formato.parse(fechaEntrega)

        if (devolucion != null && entrega != null) {

            val diferencia = entrega.time - devolucion.time

            var diasAtraso =
                TimeUnit.MILLISECONDS.toDays(diferencia)

            if (diasAtraso < 0) {
                diasAtraso = 0
            }

            val multaPorDia = 1.50
            val multaTotal = diasAtraso * multaPorDia

            val estado = if (diasAtraso > 0) {
                "Devuelto con atraso"
            } else {
                "Devuelto a tiempo"
            }

            println()
            println("===== RESULTADO =====")
            println()

            println("Titulo del libro: $tituloLibro")
            println("Tipo de usuario: $tipoUsuario")
            println("Fecha de prestamo: $fechaPrestamo")
            println("Fecha de devolucion: $fechaDevolucion")
            println("Fecha de entrega: $fechaEntrega")

            println()
            println("Estado: $estado")
            println("Dias de atraso: $diasAtraso")
            println("Multa por dia: S/ %.2f".format(multaPorDia))
            println("Multa total: S/ %.2f".format(multaTotal))

            // Mostrar cuadro de multa si existe atraso
            if (diasAtraso > 0) {

                println()
                println("============= DETALLE DE MULTA =============")
                println()

                println(
                    String.format(
                        "%-6s %-14s %-14s %-14s",
                        "DIA",
                        "FECHA",
                        "MULTA/DIA",
                        "ACUMULADO"
                    )
                )

                println("------------------------------------------------")

                val calendario = Calendar.getInstance()
                calendario.time = devolucion

                var acumulado = 0.0

                for (dia in 1..diasAtraso) {

                    // Avanzar un dia despues de la fecha limite
                    calendario.add(Calendar.DAY_OF_MONTH, 1)

                    acumulado += multaPorDia

                    val fechaActual =
                        formato.format(calendario.time)

                    println(
                        String.format(
                            "%-6d %-14s S/ %-11.2f S/ %-10.2f",
                            dia,
                            fechaActual,
                            multaPorDia,
                            acumulado
                        )
                    )
                }

                println("------------------------------------------------")
                println(
                    "MULTA TOTAL: S/ %.2f".format(multaTotal)
                )
            }

        }

    } catch (e: Exception) {

        println()
        println("Error: Ingrese las fechas correctamente.")
        println("Formato correcto: dd/MM/yyyy")
    }
}