package com.tecsup.mibodega.ui.cliente.screens.carrito

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tecsup.mibodega.ui.cliente.modelo.ItemCarrito
import com.tecsup.mibodega.ui.cliente.modelo.Producto
import com.tecsup.mibodega.ui.componentes.BotonPrimario
import com.tecsup.mibodega.ui.theme.GrisClaro
import com.tecsup.mibodega.ui.theme.RojoPrecio
import com.tecsup.mibodega.ui.theme.VerdeBodega

@Composable
fun CarritoScreen(
    carrito: List<ItemCarrito>,
    onVolver: () -> Unit,
    onIncrementar: (Producto) -> Unit,
    onDecrementar: (Producto) -> Unit,
    onEliminar: (Producto) -> Unit,
    onContinuarPedido: () -> Unit
) {
    val subtotal = carrito.sumOf { it.producto.precio * it.cantidad }
    val costoDelivery = if (carrito.isNotEmpty()) 4.00 else 0.00
    val total = if (carrito.isNotEmpty()) subtotal + costoDelivery else 0.00

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onVolver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text(
                text = "Mi carrito",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        if (carrito.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.RemoveShoppingCart,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Tu carrito está vacío",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(carrito) { item ->
                    FilaItemCarrito(
                        item = item,
                        onIncrementar = { onIncrementar(item.producto) },
                        onDecrementar = { onDecrementar(item.producto) },
                        onEliminar = { onEliminar(item.producto) }
                    )
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Subtotal", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = "S/ ${String.format("%.2f", subtotal)}", fontWeight = FontWeight.SemiBold)
                }

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Costo de delivery", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = "S/ ${String.format("%.2f", costoDelivery)}", fontWeight = FontWeight.SemiBold)
                }

                Spacer(Modifier.height(8.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Total", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(
                        text = "S/ ${String.format("%.2f", total)}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = RojoPrecio
                    )
                }

                Spacer(Modifier.height(16.dp))

                BotonPrimario(
                    texto = "Continuar pedido",
                    onClick = onContinuarPedido
                )
            }
        }
    }
}

@Composable
private fun FilaItemCarrito(
    item: ItemCarrito,
    onIncrementar: () -> Unit,
    onDecrementar: () -> Unit,
    onEliminar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(GrisClaro, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.producto.imagenRes),
                contentDescription = item.producto.nombre,
                modifier = Modifier
                    .size(46.dp)
                    .padding(2.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.producto.nombre,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "S/ ${String.format("%.2f", item.producto.precio)}",
                style = MaterialTheme.typography.bodySmall,
                color = RojoPrecio,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onDecrementar,
                modifier = Modifier.size(32.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = GrisClaro)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Disminuir",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = "${item.cantidad}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            IconButton(
                onClick = onIncrementar,
                modifier = Modifier.size(32.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = VerdeBodega)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(Modifier.width(6.dp))

            IconButton(
                onClick = onEliminar,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color(0xFF6B7280),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}