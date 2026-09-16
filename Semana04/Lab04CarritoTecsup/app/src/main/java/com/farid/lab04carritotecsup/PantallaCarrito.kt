package com.farid.lab04carritotecsup

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

private val ColorMoradoTecsup = Color(0xFF5E3F97)
private val ColorBordeGris = Color(0xFFC7CBD1)
private val ColorTextoSecundario = Color(0xFF7A8494)
private val ColorFondoTotales = Color(0xFFF4F3F8)
private val ColorRojoTacho = Color(0xFFB3261E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCarrito() {
    val productos = remember { mutableStateListOf<Producto>() }

    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }

    val totalCantidad = productos.size
    val subtotal = productos.sumOf { it.precio * it.cantidad }

    val (porcentajeDescuento, descuento) = when {
        subtotal > 5000.0 -> Pair("10%", subtotal * 0.10)
        subtotal > 3000.0 -> Pair("5%", subtotal * 0.05)
        else -> Pair("0%", 0.0)
    }

    val subtotalConDescuento = subtotal - descuento
    val igv = subtotalConDescuento * 0.18
    val totalPagar = subtotalConDescuento + igv

    if (productoAEliminar != null) {
        AlertDialog(
            onDismissRequest = { productoAEliminar = null },
            title = { Text("Eliminar producto", fontWeight = FontWeight.Bold) },
            text = { Text("¿Eliminar este producto?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        productos.remove(productoAEliminar)
                        productoAEliminar = null
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { productoAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi Carrito TECSUP",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorMoradoTecsup
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = { Text("Nombre del producto", color = ColorTextoSecundario, fontSize = 14.sp) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = ColorMoradoTecsup,
                        unfocusedIndicatorColor = ColorBordeGris
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = precio,
                        onValueChange = { precio = it },
                        placeholder = { Text("Precio (S/)", color = ColorTextoSecundario, fontSize = 14.sp) },
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1.3f),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = ColorMoradoTecsup,
                            unfocusedIndicatorColor = ColorBordeGris
                        )
                    )

                    OutlinedTextField(
                        value = cantidad,
                        onValueChange = { cantidad = it },
                        placeholder = { Text("Cantidad", color = ColorTextoSecundario, fontSize = 14.sp) },
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = ColorMoradoTecsup,
                            unfocusedIndicatorColor = ColorBordeGris
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        val precioNum = precio.toDoubleOrNull() ?: 0.0
                        val cantidadNum = cantidad.toIntOrNull() ?: 0
                        if (nombre.isNotBlank() && precioNum > 0 && cantidadNum > 0) {
                            productos.add(Producto(nombre.trim(), precioNum, cantidadNum))
                            nombre = ""
                            precio = ""
                            cantidad = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ColorMoradoTecsup)
                ) {
                    Text(
                        text = "AGREGAR",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (productos.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Tu carrito está vacío",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6B7280)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Agrega tu primer producto",
                                fontSize = 13.sp,
                                color = Color(0xFF9CA3AF)
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(productos) { producto ->
                            TarjetaProducto(
                                producto = producto,
                                onEliminar = { productoAEliminar = producto }
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorFondoTotales)
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                if (productos.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Productos: 0",
                                fontSize = 13.sp,
                                color = ColorTextoSecundario
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "TOTAL",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF1E293B)
                            )
                        }
                        Text(
                            text = "S/ 0.00",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            color = ColorMoradoTecsup
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Productos: $totalCantidad",
                            fontSize = 13.sp,
                            color = ColorTextoSecundario
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Subtotal", fontSize = 13.sp, color = Color(0xFF334155))
                            Text(String.format(Locale.US, "S/ %.2f", subtotal), fontSize = 13.sp, color = Color(0xFF334155))
                        }

                        if (descuento > 0.0) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Descuento ($porcentajeDescuento)", fontSize = 13.sp, color = Color(0xFF16A34A))
                                Text(String.format(Locale.US, "- S/ %.2f", descuento), fontSize = 13.sp, color = Color(0xFF16A34A))
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("IGV (18%)", fontSize = 13.sp, color = Color(0xFF334155))
                            Text(String.format(Locale.US, "S/ %.2f", igv), fontSize = 13.sp, color = Color(0xFF334155))
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOTAL",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF1E293B)
                            )
                            Text(
                                text = String.format(Locale.US, "S/ %.2f", totalPagar),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Black,
                                color = ColorMoradoTecsup
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaProducto(producto: Producto, onEliminar: () -> Unit) {
    val importe = producto.precio * producto.cantidad

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = String.format(Locale.US, "S/ %.2f  x %d", producto.precio, producto.cantidad),
                    fontSize = 13.sp,
                    color = ColorTextoSecundario
                )
            }

            Text(
                text = String.format(Locale.US, "S/ %.2f", importe),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = ColorMoradoTecsup
            )

            Spacer(modifier = Modifier.width(14.dp))

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onEliminar() },
                contentAlignment = Alignment.Center
            ) {
                IconoTacho(tint = ColorRojoTacho)
            }
        }
    }
}

@Composable
fun IconoTacho(tint: Color) {
    Canvas(modifier = Modifier.size(18.dp)) {
        val strokeWidth = 1.6.dp.toPx()

        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.22f, size.height * 0.28f),
            size = Size(size.width * 0.56f, size.height * 0.65f),
            cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
            style = Stroke(width = strokeWidth)
        )

        drawLine(
            color = tint,
            start = Offset(size.width * 0.12f, size.height * 0.28f),
            end = Offset(size.width * 0.88f, size.height * 0.28f),
            strokeWidth = strokeWidth
        )

        drawRoundRect(
            color = tint,
            topLeft = Offset(size.width * 0.38f, size.height * 0.12f),
            size = Size(size.width * 0.24f, size.height * 0.16f),
            cornerRadius = CornerRadius(1.dp.toPx(), 1.dp.toPx()),
            style = Stroke(width = strokeWidth)
        )

        drawLine(
            color = tint,
            start = Offset(size.width * 0.40f, size.height * 0.40f),
            end = Offset(size.width * 0.40f, size.height * 0.78f),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.60f, size.height * 0.40f),
            end = Offset(size.width * 0.60f, size.height * 0.78f),
            strokeWidth = strokeWidth
        )
    }
}