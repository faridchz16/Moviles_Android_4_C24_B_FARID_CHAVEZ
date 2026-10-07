package com.tecsup.mibodega.ui.cliente.screens.entrega

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tecsup.mibodega.ui.cliente.modelo.ItemCarrito
import com.tecsup.mibodega.ui.cliente.modelo.RepositorioUsuarios
import com.tecsup.mibodega.ui.componentes.BotonPrimario
import com.tecsup.mibodega.ui.theme.RojoPrecio
import com.tecsup.mibodega.ui.theme.VerdeBodega

enum class TipoEntrega {
    DELIVERY,
    RECOJO_EN_TIENDA
}

@Composable
fun DatosEntregaScreen(
    subtotal: Double = 0.0,
    carrito: List<ItemCarrito> = emptyList(),
    onVolver: () -> Unit,
    onConfirmarPedido: (total: Double, tipo: TipoEntrega, direccion: String, metodo: String) -> Unit
) {
    val usuarioActual = RepositorioUsuarios.usuarioActivo

    var nombre by remember { mutableStateOf(usuarioActual?.nombre ?: "") }
    var telefono by remember { mutableStateOf(usuarioActual?.telefono ?: "") }
    var direccion by remember { mutableStateOf(usuarioActual?.direccion ?: "") }
    var referencia by remember { mutableStateOf(usuarioActual?.referencia ?: "") }

    var tipoEntrega by remember { mutableStateOf(TipoEntrega.DELIVERY) }
    val metodosPago = listOf("Efectivo al entregar", "Yape", "Plin")
    var metodoSeleccionado by remember { mutableStateOf(metodosPago[0]) }

    var intentoConfirmar by remember { mutableStateOf(false) }

    val nombreInvalido = intentoConfirmar && nombre.isBlank()
    val telefonoInvalido = intentoConfirmar && telefono.isBlank()
    val direccionInvalida = intentoConfirmar && tipoEntrega == TipoEntrega.DELIVERY && direccion.isBlank()

    val costoEnvio = if (tipoEntrega == TipoEntrega.DELIVERY) 4.00 else 0.00
    val totalPagar = subtotal + costoEnvio

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
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
            Text(
                text = "Datos de entrega",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(12.dp))

            Text(
                text = "Modalidad de entrega",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .border(
                        1.dp,
                        if (tipoEntrega == TipoEntrega.DELIVERY) VerdeBodega else MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable { tipoEntrega = TipoEntrega.DELIVERY }
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (tipoEntrega == TipoEntrega.DELIVERY),
                    onClick = { tipoEntrega = TipoEntrega.DELIVERY },
                    colors = RadioButtonDefaults.colors(selectedColor = VerdeBodega)
                )
                Icon(
                    imageVector = Icons.Outlined.LocalShipping,
                    contentDescription = null,
                    tint = VerdeBodega,
                    modifier = Modifier.padding(start = 4.dp, end = 8.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Envío a domicilio",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Llega a tu puerta (+ S/ 4.00)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .border(
                        1.dp,
                        if (tipoEntrega == TipoEntrega.RECOJO_EN_TIENDA) VerdeBodega else MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable { tipoEntrega = TipoEntrega.RECOJO_EN_TIENDA }
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (tipoEntrega == TipoEntrega.RECOJO_EN_TIENDA),
                    onClick = { tipoEntrega = TipoEntrega.RECOJO_EN_TIENDA },
                    colors = RadioButtonDefaults.colors(selectedColor = VerdeBodega)
                )
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = VerdeBodega,
                    modifier = Modifier.padding(start = 4.dp, end = 8.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Recojo en tienda",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "En local central (Gratis)",
                        style = MaterialTheme.typography.bodySmall,
                        color = VerdeBodega
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("Nombre completo", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                singleLine = true,
                isError = nombreInvalido,
                supportingText = {
                    if (nombreInvalido) {
                        Text("El nombre es obligatorio", color = MaterialTheme.colorScheme.error)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = VerdeBodega,
                    errorBorderColor = MaterialTheme.colorScheme.error
                )
            )

            Spacer(Modifier.height(8.dp))

            Text("Teléfono de contacto", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = telefonoInvalido,
                supportingText = {
                    if (telefonoInvalido) {
                        Text("El teléfono es obligatorio", color = MaterialTheme.colorScheme.error)
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = VerdeBodega,
                    errorBorderColor = MaterialTheme.colorScheme.error
                )
            )

            if (tipoEntrega == TipoEntrega.DELIVERY) {
                Spacer(Modifier.height(8.dp))

                Text("Dirección de entrega", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    singleLine = true,
                    isError = direccionInvalida,
                    supportingText = {
                        if (direccionInvalida) {
                            Text("La dirección es obligatoria", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedBorderColor = VerdeBodega,
                        errorBorderColor = MaterialTheme.colorScheme.error
                    )
                )

                Spacer(Modifier.height(8.dp))

                Text("Referencia (Opcional)", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                OutlinedTextField(
                    value = referencia,
                    onValueChange = { referencia = it },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedBorderColor = VerdeBodega
                    )
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Método de pago",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            metodosPago.forEach { metodo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(
                            1.dp,
                            if (metodo == metodoSeleccionado) VerdeBodega else MaterialTheme.colorScheme.outlineVariant,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable { metodoSeleccionado = metodo }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (metodo == metodoSeleccionado),
                        onClick = { metodoSeleccionado = metodo },
                        colors = RadioButtonDefaults.colors(selectedColor = VerdeBodega)
                    )
                    Text(
                        text = metodo,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Costo de entrega", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(
                    text = if (costoEnvio == 0.0) "Gratis" else "S/ ${String.format("%.2f", costoEnvio)}",
                    fontWeight = FontWeight.SemiBold,
                    color = if (costoEnvio == 0.0) VerdeBodega else MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Total a pagar", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(
                    text = "S/ ${String.format("%.2f", totalPagar)}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RojoPrecio
                )
            }

            Spacer(Modifier.height(12.dp))

            BotonPrimario(
                texto = "Confirmar pedido",
                onClick = {
                    intentoConfirmar = true
                    val esValido = if (tipoEntrega == TipoEntrega.DELIVERY) {
                        nombre.isNotBlank() && telefono.isNotBlank() && direccion.isNotBlank()
                    } else {
                        nombre.isNotBlank() && telefono.isNotBlank()
                    }

                    if (esValido) {
                        val direccionFinal = if (tipoEntrega == TipoEntrega.DELIVERY) {
                            if (referencia.isNotBlank()) "$direccion ($referencia)" else direccion
                        } else {
                            "Recojo en tienda principal"
                        }
                        onConfirmarPedido(totalPagar, tipoEntrega, direccionFinal, metodoSeleccionado)
                    }
                }
            )
        }
    }
}