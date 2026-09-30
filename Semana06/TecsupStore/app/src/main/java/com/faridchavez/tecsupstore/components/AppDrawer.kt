package com.faridchavez.tecsupstore.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

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
        modifier = modifier.width(300.dp),
        drawerContainerColor = Color.White
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        val opciones = listOf(
            DestinoDrawer.Inicio,
            DestinoDrawer.Pedidos,
            DestinoDrawer.Favoritos,
            DestinoDrawer.Perfil,
            DestinoDrawer.Salir
        )

        opciones.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.titulo) },
                selected = false,
                onClick = { onNavegar(item.ruta) },
                icon = {
                    Icon(
                        imageVector = item.icono,
                        contentDescription = item.titulo,
                        tint = Color(0xFF49454F)
                    )
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}