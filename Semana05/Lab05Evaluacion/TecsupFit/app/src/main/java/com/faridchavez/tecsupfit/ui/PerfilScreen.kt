package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val VerdeTecsup = Color(0xFF0D634C)
private val VerdeClaroIcono = Color(0xFFE2F3EE)
private val FondoGrisClaro = Color(0xFFF6F8F7)
private val GrisSubtexto = Color(0xFF757575)

@Composable
fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoGrisClaro)
            .padding(20.dp)
    ) {
        Text(
            text = "Mi Perfil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta con datos del usuario
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar con inicial
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(VerdeTecsup),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "D",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Diego Alarcón",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Estudiante Tecsup",
                        fontSize = 13.sp,
                        color = GrisSubtexto
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Plan Activo: Libre",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = VerdeTecsup
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Información de la cuenta",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = GrisSubtexto
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Opciones de configuración / perfil
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                ItemOpcionPerfil(
                    icono = Icons.Default.Email,
                    titulo = "Correo institucional",
                    subtitulo = "diego.alarcon@tecsup.edu.pe"
                )
                Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                ItemOpcionPerfil(
                    icono = Icons.Default.FitnessCenter,
                    titulo = "Membresía del gimnasio",
                    subtitulo = "Sede Central - Acceso total"
                )
                Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                ItemOpcionPerfil(
                    icono = Icons.Default.Settings,
                    titulo = "Configuración",
                    subtitulo = "Preferencias y notificaciones"
                )
            }
        }
    }
}

@Composable
private fun ItemOpcionPerfil(
    icono: ImageVector,
    titulo: String,
    subtitulo: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(VerdeClaroIcono),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = VerdeTecsup,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = subtitulo,
                fontSize = 12.sp,
                color = GrisSubtexto
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color(0xFFBDBDBD),
            modifier = Modifier.size(18.dp)
        )
    }
}