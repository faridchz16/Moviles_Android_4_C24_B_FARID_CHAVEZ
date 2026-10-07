package com.faridchavez.tecsupfit.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.model.ClaseFit

private val VerdeTecsup = Color(0xFF0D634C)
private val VerdeFondoIlustracion = Color(0xFFE2F3EE)

@Composable
fun DetalleClaseScreen(
    clase: ClaseFit,
    onVolver: () -> Unit,
    onReservar: () -> Unit
) {
    val context = LocalContext.current
    var mostrarModalConfirmacion by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        // Flecha y título: ← Detalle de clase
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onVolver)
                .padding(bottom = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color(0xFF1E293B),
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "  Detalle de clase",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1E293B)
            )
        }

        // Caja de ilustración central
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(VerdeFondoIlustracion, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = VerdeTecsup,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre de la clase
        Text(
            text = clase.titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Horario, sala y duración
        Text(
            text = "${clase.horario} • ${clase.sala} • ${clase.duracionMin} min",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Descripción
        Text(
            text = clase.descripcion,
            fontSize = 13.sp,
            color = Color(0xFF475569),
            lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Cupos disponibles
        Text(
            text = "${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        // Botón "Reservar cupo"
        Button(
            onClick = { mostrarModalConfirmacion = true }, // Abre el modal de confirmación
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeTecsup),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Reservar cupo",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

    // Modal emergente de Confirmación
    if (mostrarModalConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarModalConfirmacion = false },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.White,
            title = {
                Text(
                    text = "Confirmar Reserva",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF0F172A)
                )
            },
            text = {
                Text(
                    text = "¿Deseas reservar la clase de \"${clase.titulo}\" en el horario ${clase.horario} - ${clase.sala}?",
                    fontSize = 13.sp,
                    color = Color(0xFF475569)
                )
            },
            dismissButton = {
                TextButton(onClick = { mostrarModalConfirmacion = false }) {
                    Text(text = "Cancelar", color = Color.Gray, fontSize = 13.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarModalConfirmacion = false
                        Toast.makeText(
                            context,
                            "Reserva confirmada para ${clase.titulo}",
                            Toast.LENGTH_SHORT
                        ).show()
                        onReservar() // Pasa a la pantalla ¡Cupo reservado!
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeTecsup),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Confirmar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }
            }
        )
    }
}