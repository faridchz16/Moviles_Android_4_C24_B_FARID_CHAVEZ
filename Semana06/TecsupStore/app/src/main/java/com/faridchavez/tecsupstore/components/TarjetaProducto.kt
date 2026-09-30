package com.faridchavez.tecsupstore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    var esFavorito: Boolean = false
)

@Composable
fun TarjetaProducto(
    producto: Producto,
    onToggleFavorito: (Producto) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F5FA)),
        border = if (expanded) BorderStroke(1.5.dp, Color(0xFF5E2B88)) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(Color(0xFFEDE7F6), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = null,
                    tint = Color(0xFF5E2B88),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "S/ ${"%.2f".format(producto.precio)}",
                    fontSize = 13.sp,
                    color = Color(0xFF757575)
                )
            }

            Box {
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFEDE7F6).copy(alpha = 0.6f), RoundedCornerShape(18.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Opciones",
                        tint = Color(0xFF5E2B88),
                        modifier = Modifier.size(20.dp)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(170.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Favoritos",
                                fontSize = 13.sp,
                                color = Color(0xFF333333)
                            )
                        },
                        onClick = {
                            onToggleFavorito(producto)
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (producto.esFavorito) Color.Red else Color(0xFF333333),
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    )
                    HorizontalDivider(color = Color(0xFFEFEFEF), thickness = 1.dp)
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Compartir",
                                fontSize = 13.sp,
                                color = Color(0xFF333333)
                            )
                        },
                        onClick = { expanded = false },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = null,
                                tint = Color(0xFF333333),
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    )
                    HorizontalDivider(color = Color(0xFFEFEFEF), thickness = 1.dp)
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Reportar",
                                fontSize = 13.sp,
                                color = Color(0xFF333333)
                            )
                        },
                        onClick = { expanded = false },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = Color(0xFF666666),
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}