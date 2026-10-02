package com.faridchavez.tecsupstore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class DestinoDrawer(val ruta: String, val titulo: String, val icono: ImageVector) {
    object Inicio : DestinoDrawer("inicio", "Inicio", Icons.Default.Home)
    object Pedidos : DestinoDrawer("pedidos", "Mis pedidos", Icons.Default.ShoppingBag)
    object Favoritos : DestinoDrawer("favoritos", "Favoritos", Icons.Default.Favorite)
    object Perfil : DestinoDrawer("perfil", "Perfil", Icons.Default.Person)
    object Salir : DestinoDrawer("salir", "Cerrar sesion", Icons.AutoMirrored.Filled.ExitToApp)
}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.size(52.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                border = BorderStroke(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f),
                ),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "MR",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Maria Rojas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )

                Text(
                    text = "maria@tecsup.edu.pe",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                )

                Spacer(modifier = Modifier.height(6.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        text = "Estudiante Tecsup",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}

@Composable
fun AppDrawerContent(
    rutaActual: String,
    onNavegar: (String) -> Unit,
    modifier: Modifier = Modifier,
    cantidadFavoritos: Int = 0,
) {
    ModalDrawerSheet(
        modifier = modifier.width(310.dp),
        drawerContainerColor = Color.White,
    ) {
        DrawerHeader()

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            color = Color(0xFFEEEEEE),
            thickness = 1.dp,
        )

        val opciones = listOf(
            DestinoDrawer.Inicio,
            DestinoDrawer.Pedidos,
            DestinoDrawer.Favoritos,
            DestinoDrawer.Perfil,
            DestinoDrawer.Salir,
        )

        opciones.forEach { item ->
            val seleccionado = item.ruta == rutaActual

            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.titulo,
                        fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Normal,
                        color = if (seleccionado) Color(0xFF5E2B88) else Color(0xFF49454F),
                        fontSize = 14.sp,
                    )
                },
                selected = seleccionado,
                onClick = { onNavegar(item.ruta) },
                icon = {
                    Icon(
                        imageVector = item.icono,
                        contentDescription = item.titulo,
                        tint = if (seleccionado) Color(0xFF5E2B88) else Color(0xFF49454F),
                        modifier = Modifier.size(20.dp),
                    )
                },
                badge = {
                    if ((item == DestinoDrawer.Favoritos) && (cantidadFavoritos > 0)) {
                        Badge(
                            containerColor = Color(0xFF5E2B88),
                            contentColor = Color.White,
                        ) {
                            Text(
                                text = cantidadFavoritos.toString(),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFF3E5F5),
                    unselectedContainerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp),
            )
        }
    }
}