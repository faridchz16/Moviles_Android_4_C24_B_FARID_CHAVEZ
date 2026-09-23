package com.faridchavez.clinicasalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.clinicasalud.ui.HomeScreen
import com.faridchavez.clinicasalud.ui.theme.ClinicaSaludTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClinicaSaludTheme {
                ClinicaApp()
            }
        }
    }
}

@Composable
fun ClinicaApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var destinoActual by remember { mutableStateOf("Inicio") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(280.dp)
                    .fillMaxHeight(),
                drawerContainerColor = Color.White,
                drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEDE7F6)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "JP",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color(0xFF4A148C)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = "Juan Pérez",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF1E1926)
                            )
                            Text(
                                text = "Paciente",
                                fontSize = 13.sp,
                                color = Color(0xFF7E768A)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(20.dp))

                    DrawerMenuItem(
                        titulo = "Inicio",
                        seleccionado = destinoActual == "Inicio",
                        onClick = {
                            destinoActual = "Inicio"
                            scope.launch { drawerState.close() }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    DrawerMenuItem(
                        titulo = "Mis citas",
                        seleccionado = destinoActual == "Mis citas",
                        onClick = {
                            destinoActual = "Mis citas"
                            scope.launch { drawerState.close() }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    DrawerMenuItem(
                        titulo = "Historial médico",
                        seleccionado = destinoActual == "Historial médico",
                        onClick = {
                            destinoActual = "Historial médico"
                            scope.launch { drawerState.close() }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    DrawerMenuItem(
                        titulo = "Perfil",
                        seleccionado = destinoActual == "Perfil",
                        onClick = {
                            destinoActual = "Perfil"
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when (destinoActual) {
                "Inicio" -> {
                    HomeScreen(
                        paddingValues = innerPadding,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        },
                        onMedicoClick = {}
                    )
                }
                "Mis citas" -> {
                    CitasScreen(
                        paddingValues = innerPadding,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }
                else -> {
                    HomeScreen(
                        paddingValues = innerPadding,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        },
                        onMedicoClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun DrawerMenuItem(
    titulo: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    val fondoColor = if (seleccionado) Color(0xFFEDE7F6) else Color.Transparent
    val textoColor = if (seleccionado) Color(0xFF4A148C) else Color(0xFF1E1926)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(fondoColor)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .border(width = 1.8.dp, color = Color(0xFF555555), shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = titulo,
                fontSize = 15.sp,
                fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Normal,
                color = textoColor
            )
        }
    }
}