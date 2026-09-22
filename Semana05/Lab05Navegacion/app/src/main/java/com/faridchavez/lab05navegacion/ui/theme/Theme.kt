package com.faridchavez.lab05navegacion.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = MoradoPrincipal,
    onPrimary = FondoBlanco,
    primaryContainer = MoradoClaro,
    onPrimaryContainer = TextoOscuro,
    background = FondoBlanco,
    onBackground = TextoOscuro,
    surface = FondoBlanco,
    onSurface = TextoOscuro,
    surfaceVariant = GrisTarjeta,
    onSurfaceVariant = TextoOscuro
)

@Composable
fun Lab05NavegacionTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}