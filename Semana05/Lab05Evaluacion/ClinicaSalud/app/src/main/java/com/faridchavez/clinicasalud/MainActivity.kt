package com.faridchavez.clinicasalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.faridchavez.clinicasalud.data.listaCitasIniciales
import com.faridchavez.clinicasalud.data.listaMedicos
import com.faridchavez.clinicasalud.model.Cita
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

data class RegistroHistorial(
    val id: Int,
    val diagnostico: String,
    val doctor: String,
    val especialidad: String,
    val fecha: String
)

val historialEjemplo = listOf(
    RegistroHistorial(1, "Chequeo preventivo anual", "Dr. Luis Vega", "Pediatría", "10 Sep 2026"),
    RegistroHistorial(2, "Evaluación de arritmia leve", "Dra. Ana Torres", "Cardiología", "15 Ago 2026"),
    RegistroHistorial(3, "Tratamiento dermatológico", "Dra. Rosa Díaz", "Dermatología", "02 Jul 2026")
)

@Composable
fun ClinicaApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var destinoActual by remember { mutableStateOf("Inicio") }
    var medicoSeleccionadoId by remember { mutableIntStateOf(1) }

    val citas = remember { mutableStateListOf<Cita>().apply { addAll(listaCitasIniciales) } }
    var ultimaCitaAgendada by remember { mutableStateOf<Cita?>(null) }

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
                    HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
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
                        onMedicoClick = { id ->
                            medicoSeleccionadoId = id
                            destinoActual = "Detalle"
                        }
                    )
                }
                "Mis citas" -> {
                    CitasScreen(
                        paddingValues = innerPadding,
                        citas = citas,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }
                "Historial médico" -> {
                    HistorialMedicoScreen(
                        paddingValues = innerPadding,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }
                "Perfil" -> {
                    PerfilScreen(
                        paddingValues = innerPadding,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }
                "Detalle" -> {
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MedicoDetailScreen(
                            medicoId = medicoSeleccionadoId,
                            onBackClick = { destinoActual = "Inicio" },
                            onAgendarClick = { destinoActual = "Agendar" }
                        )
                    }
                }
                "Agendar" -> {
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AgendarCitaScreen(
                            medicoId = medicoSeleccionadoId,
                            onBackClick = { destinoActual = "Detalle" },
                            onConfirmar = { nuevaCita ->
                                citas.add(0, nuevaCita)
                                ultimaCitaAgendada = nuevaCita
                                destinoActual = "Confirmacion"
                            }
                        )
                    }
                }
                "Confirmacion" -> {
                    Box(modifier = Modifier.padding(innerPadding)) {
                        ConfirmacionScreen(
                            cita = ultimaCitaAgendada,
                            onVerCitasClick = { destinoActual = "Mis citas" }
                        )
                    }
                }
                else -> {
                    HomeScreen(
                        paddingValues = innerPadding,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        },
                        onMedicoClick = { id ->
                            medicoSeleccionadoId = id
                            destinoActual = "Detalle"
                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialMedicoScreen(
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFFAFAFA))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Historial médico",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF1E1926)
                )
            },
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = Color(0xFF1E1926)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(historialEjemplo) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.diagnostico,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1E1926)
                            )
                            Text(
                                text = item.fecha,
                                fontSize = 12.sp,
                                color = Color(0xFF7E768A)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "${item.doctor} · ${item.especialidad}",
                            fontSize = 13.sp,
                            color = Color(0xFF4A148C),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Mi Perfil",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF1E1926)
                )
            },
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = Color(0xFF1E1926)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEDE7F6)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF4A148C),
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Juan Pérez", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF1E1926))
            Text(text = "juan.perez@clinicasalud.com", color = Color(0xFF7E768A), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE))
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Tipo de documento:", color = Color(0xFF7E768A), fontSize = 14.sp)
                Text("DNI", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Número:", color = Color(0xFF7E768A), fontSize = 14.sp)
                Text("72819234", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Seguro médico:", color = Color(0xFF7E768A), fontSize = 14.sp)
                Text("Plan Salud Plus", fontWeight = FontWeight.SemiBold, color = Color(0xFF2E7D32), fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun MedicoDetailScreen(
    medicoId: Int,
    onBackClick: () -> Unit,
    onAgendarClick: (Int) -> Unit
) {
    val medico = listaMedicos.find { it.id == medicoId } ?: listaMedicos.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar",
                    tint = Color(0xFF1E1926)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Perfil del médico",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1926)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEDE7F6)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color(0xFF4A148C),
                    modifier = Modifier.size(46.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = medico.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1926)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${medico.especialidad} · 12 años exp.",
                fontSize = 13.sp,
                color = Color(0xFF7E768A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFA000),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${medico.calificacion} (128 reseñas)",
                    fontSize = 13.sp,
                    color = Color(0xFF555555)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = medico.descripcion,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color(0xFF444444)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onAgendarClick(medico.id) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C))
        ) {
            Text(
                text = "Agendar cita",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun AgendarCitaScreen(
    medicoId: Int,
    onBackClick: () -> Unit,
    onConfirmar: (Cita) -> Unit
) {
    val medico = listaMedicos.find { it.id == medicoId } ?: listaMedicos.first()

    val dias = listOf("Jue\n26", "Vie\n27", "Sáb\n28")
    val horas = listOf("9:00", "10:30", "3:00")

    var diaSeleccionado by remember { mutableStateOf("Vie\n27") }
    var horaSeleccionada by remember { mutableStateOf("10:30") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar",
                    tint = Color(0xFF1E1926)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Agendar cita",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1926)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Selecciona fecha",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7E768A)
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            items(dias) { dia ->
                val esSel = diaSeleccionado == dia
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (esSel) Color(0xFF4A148C) else Color(0xFFF3EDF7),
                    modifier = Modifier
                        .size(width = 72.dp, height = 76.dp)
                        .clickable { diaSeleccionado = dia }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = dia,
                            fontSize = 15.sp,
                            fontWeight = if (esSel) FontWeight.Bold else FontWeight.Medium,
                            color = if (esSel) Color.White else Color(0xFF1E1926),
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Selecciona hora",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7E768A)
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            items(horas) { hora ->
                val esSel = horaSeleccionada == hora
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (esSel) Color(0xFF4A148C) else Color(0xFFF3EDF7),
                    modifier = Modifier
                        .size(width = 86.dp, height = 48.dp)
                        .clickable { horaSeleccionada = hora }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = hora,
                            fontSize = 14.sp,
                            fontWeight = if (esSel) FontWeight.Bold else FontWeight.Medium,
                            color = if (esSel) Color.White else Color(0xFF1E1926)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val fechaFormateada = when (diaSeleccionado) {
                    "Jue\n26" -> "Jueves 26"
                    "Vie\n27" -> "Viernes 27"
                    else -> "Sábado 28"
                }
                val nueva = Cita(
                    id = System.currentTimeMillis().toInt(),
                    medicoNombre = medico.nombre,
                    especialidad = medico.especialidad,
                    fecha = fechaFormateada,
                    hora = "$horaSeleccionada am",
                    estado = "Confirmada"
                )
                onConfirmar(nueva)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C))
        ) {
            Text(
                text = "Confirmar cita",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ConfirmacionScreen(
    cita: Cita?,
    onVerCitasClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8F5E9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color(0xFF2E7D32),
                modifier = Modifier.size(42.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¡Cita agendada!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1926)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = cita?.medicoNombre ?: "Dra. Ana Torres",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF555555)
        )

        Text(
            text = "${cita?.fecha ?: "Viernes 27"}, ${cita?.hora ?: "10:30 am"}",
            fontSize = 14.sp,
            color = Color(0xFF7E768A)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = onVerCitasClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(180.dp)
                .height(44.dp)
        ) {
            Text(
                text = "Ver mis citas",
                color = Color(0xFF4A148C),
                fontWeight = FontWeight.Medium
            )
        }
    }
}