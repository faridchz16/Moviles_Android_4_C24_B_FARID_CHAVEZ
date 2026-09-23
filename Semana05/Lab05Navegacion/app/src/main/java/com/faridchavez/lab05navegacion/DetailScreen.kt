package com.faridchavez.lab05navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    itemId: Int,
    onBack: () -> Unit
) {
    val alumnoInfo = when (itemId) {
        1 -> DetailInfo("Juan León", "Ingeniería de Sistemas", "juan.leon@example.com", "Estudiante destacado con interés en el desarrollo móvil y arquitecturas limpias.")
        2 -> DetailInfo("Maria Garcia", "Arquitectura", "maria.garcia@example.com", "Estudiante apasionada por el diseño urbano sostenible y la construcción moderna.")
        3 -> DetailInfo("Carlos Perez", "Medicina", "carlos.perez@example.com", "Estudiante enfocado en investigación clínica y salud comunitaria.")
        4 -> DetailInfo("Ana Lopez", "Derecho", "ana.lopez@example.com", "Estudiante orientada al derecho corporativo y resolución de conflictos.")
        else -> DetailInfo("Luis Ramirez", "Administración", "luis.ramirez@example.com", "Estudiante con enfoque en gestión de empresas y finanzas corporativas.")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Expediente Académico", style = MaterialTheme.typography.titleLarge, color = Color(0xFF1C1B1F))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color(0xFF1C1B1F)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFEF7FF))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
                    .background(Color(0xFF6750A4))
            )

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .offset(y = (-48.dp))
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8DEF8)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = alumnoInfo.nombre.split(" ").map { it.take(1) }.joinToString(""),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF6750A4)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = (-32.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = alumnoInfo.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF1C1B1F),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = alumnoInfo.carrera,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF49454F),
                    textAlign = TextAlign.Center
                )
            }

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = (-16.dp)),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(0xFFF3EDF7)
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DetailItem(
                        icon = Icons.Default.Badge,
                        label = "ID Estudiantil",
                        value = "TEC-2026-0$itemId"
                    )
                    HorizontalDivider(color = Color(0xFFCAC4D0).copy(alpha = 0.5f))

                    DetailItem(
                        icon = Icons.Default.Email,
                        label = "Correo Electrónico",
                        value = alumnoInfo.email
                    )
                    HorizontalDivider(color = Color(0xFFCAC4D0).copy(alpha = 0.5f))

                    DetailItem(
                        icon = Icons.Default.Business,
                        label = "Facultad / Sede",
                        value = "Tecsup - Lima / Sede Principal"
                    )
                    HorizontalDivider(color = Color(0xFFCAC4D0).copy(alpha = 0.5f))

                    DetailItem(
                        icon = Icons.Default.Description,
                        label = "Biografía Académica",
                        value = alumnoInfo.bio
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFE8DEF8)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color(0xFF6750A4)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFF6750A4)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF1C1B1F)
            )
        }
    }
}

data class DetailInfo(
    val nombre: String,
    val carrera: String,
    val email: String,
    val bio: String
)
