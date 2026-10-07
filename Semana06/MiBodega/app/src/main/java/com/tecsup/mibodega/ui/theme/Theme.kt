package com.tecsup.mibodega.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ClaroColorScheme = lightColorScheme(
    primary = VerdeBodega,
    onPrimary = Blanco,
    secondary = AzulEnlace,
    background = Blanco,
    onBackground = AzulTexto,
    surface = Blanco,
    onSurface = AzulTexto,
    surfaceVariant = GrisClaro,
    onSurfaceVariant = GrisTexto,
    outline = GrisBorde,
    error = RojoPrecio
)

private val OscuroColorScheme = darkColorScheme(
    primary = VerdeBodega,
    onPrimary = Blanco,
    secondary = AzulEnlace,
    background = Color(0xFF121212),
    onBackground = Color(0xFFECEFF1),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFECEFF1),
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFB0BEC5),
    outline = Color(0xFF424242),
    error = RojoPrecio
)

@Composable
fun BodegaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) OscuroColorScheme else ClaroColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = BodegaTypography,
        content = content
    )
}