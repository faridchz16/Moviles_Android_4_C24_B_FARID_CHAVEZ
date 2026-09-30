package com.faridchavez.tecsupstore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class DestinoDrawer(val ruta: String, val titulo: String, val icono: ImageVector) {
    object Inicio : DestinoDrawer("inicio", "Inicio", Icons.Outlined.Circle)
    object Pedidos : DestinoDrawer("pedidos", "Mis pedidos", Icons.Outlined.Circle)
    object Favoritos : DestinoDrawer("favoritos", "Favoritos", Icons.Outlined.Circle)
    object Perfil : DestinoDrawer("perfil", "Perfil", Icons.Outlined.Circle)
    object Salir : DestinoDrawer("salir", "Cerrar sesion", Icons.Outlined.Circle)
}

@Composable
fun AppDrawerContent(
    rutaActual: String,
    onNavegar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        modifier = modifier.width(310.dp),
        drawerContainerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(Color(0xFFEDE7F6), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "MR",
                        color = Color(0xFF5E2B88),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Maria Rojas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "maria@tecsup.edu.pe",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
        }

        val opciones = listOf(
            DestinoDrawer.Inicio,
            DestinoDrawer.Pedidos,
            DestinoDrawer.Favoritos,
            DestinoDrawer.Perfil,
            DestinoDrawer.Salir
        )

        opciones.forEach { item ->
            val seleccionado = item.ruta == rutaActual

            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.titulo,
                        fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Normal,
                        color = if (seleccionado) Color(0xFF5E2B88) else Color(0xFF49454F),
                        fontSize = 14.sp
                    )
                },
                selected = seleccionado,
                onClick = { onNavegar(item.ruta) },
                icon = {
                    Icon(
                        imageVector = item.icono,
                        contentDescription = item.titulo,
                        tint = if (seleccionado) Color(0xFF5E2B88) else Color(0xFF49454F),
                        modifier = Modifier.size(20.dp)
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFF3E5F5),
                    unselectedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}