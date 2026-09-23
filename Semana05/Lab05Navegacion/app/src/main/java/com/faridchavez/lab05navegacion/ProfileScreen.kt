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
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onGoHome: () -> Unit,
    onLogout: () -> Unit
) {
    val gradientHeader = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF6750A4),
            Color(0xFF7B1FA2)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Configuración de Perfil", style = MaterialTheme.typography.titleLarge, color = Color(0xFF1C1B1F))
                },
                navigationIcon = {
                    IconButton(onClick = onGoHome) {
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
                    .background(gradientHeader)
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
                        text = "FC",
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Farid Chavez",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF1C1B1F),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = (-16.dp)),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "INFORMACIÓN PERSONAL",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF6750A4)
                )

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFF3EDF7)),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        ProfileInfoItem(
                            icon = Icons.Default.Email,
                            label = "Correo Institucional (Ejemplo)",
                            value = "farid.chavez@tecsup.edu.pe"
                        )
                        HorizontalDivider(color = Color(0xFFCAC4D0).copy(alpha = 0.5f))
                        ProfileInfoItem(
                            icon = Icons.Default.Phone,
                            label = "Teléfono de Contacto (Ejemplo)",
                            value = "+51 987 654 321"
                        )
                    }
                }

                Text(
                    text = "ACADÉMICO",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF6750A4)
                )

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFF3EDF7)),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        ProfileInfoItem(
                            icon = Icons.Default.School,
                            label = "Carrera Profesional",
                            value = "Desarrollo de Software"
                        )
                        HorizontalDivider(color = Color(0xFFCAC4D0).copy(alpha = 0.5f))
                        ProfileInfoItem(
                            icon = Icons.Default.Person,
                            label = "Ciclo Actual",
                            value = "IV Ciclo"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                ElevatedButton(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color(0xFFFFD8E4),
                        contentColor = Color(0xFF31111D)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = null)
                        Text(text = "Cerrar Sesión", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
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
