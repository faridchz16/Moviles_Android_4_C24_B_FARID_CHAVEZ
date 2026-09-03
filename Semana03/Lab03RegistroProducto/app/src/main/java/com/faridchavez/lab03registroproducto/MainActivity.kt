package com.faridchavez.lab03registroproducto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.faridchavez.lab03registroproducto.ui.theme.Lab03RegistroProductoTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03RegistroProductoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaRegistro(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PantallaRegistro(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf<String?>(null) }
    var mostrarResumen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nuevo producto",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Completa los datos y presiona Agregar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                errorMensaje = null
            },
            label = { Text("Nombre del producto") },
            isError = errorMensaje != null && nombre.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = precio,
                onValueChange = {
                    precio = it
                    errorMensaje = null
                },
                label = { Text("Precio (S/)") },
                isError = errorMensaje != null && (precio.toDoubleOrNull() ?: 0.0) <= 0,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = cantidad,
                onValueChange = {
                    cantidad = it
                    errorMensaje = null
                },
                label = { Text("Cantidad") },
                isError = errorMensaje != null && (cantidad.toIntOrNull() ?: 0) <= 0,
                modifier = Modifier.weight(1f)
            )
        }

        if (errorMensaje != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMensaje!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    val p = precio.toDoubleOrNull()
                    val c = cantidad.toIntOrNull()

                    when {
                        nombre.isBlank() -> {
                            errorMensaje = "Ingresa el nombre del producto"
                            mostrarResumen = false
                        }
                        p == null || p <= 0.0 -> {
                            errorMensaje = "El precio debe ser un número mayor a 0"
                            mostrarResumen = false
                        }
                        c == null || c <= 0 -> {
                            errorMensaje = "La cantidad debe ser un entero mayor a 0"
                            mostrarResumen = false
                        }
                        else -> {
                            errorMensaje = null
                            mostrarResumen = true
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("AGREGAR")
            }

            Spacer(modifier = Modifier.width(12.dp))

            OutlinedButton(
                onClick = {
                    nombre = ""
                    precio = ""
                    cantidad = ""
                    errorMensaje = null
                    mostrarResumen = false
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("LIMPIAR")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (mostrarResumen) {
            val precioNum = precio.toDoubleOrNull() ?: 0.0
            val cantidadNum = cantidad.toIntOrNull() ?: 0
            val importe = precioNum * cantidadNum

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = nombre,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Precio: S/ " + String.format(Locale.getDefault(), "%.2f", precioNum))
                    Text(text = "Cantidad: $cantidadNum")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Importe total: S/ " + String.format(Locale.getDefault(), "%.2f", importe),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "✓ Producto registrado correctamente",
                color = Color(0xFF2E7D32),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text(
                text = "Aún no has registrado ningún producto",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}