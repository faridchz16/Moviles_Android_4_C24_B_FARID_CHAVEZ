package com.faridchavez.tecsupfit.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallMade
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.model.ClaseFit

val VerdeInstitucional = Color(0xFF0D634C)
val VerdeClaroIcono = Color(0xFFE2F3EE)
val GrisBorde = Color(0xFFDDE3EA)
val GrisFondoCard = Color(0xFFF9FBFA)

@Composable
fun HomeScreen(
    clases: List<ClaseFit> = emptyList(),
    onSeleccionarClase: (ClaseFit) -> Unit = {}
) {
    val context = LocalContext.current

    val catalogoClases = remember {
        listOf(
            ClaseFit(
                id = 1,
                titulo = "Yoga funcional",
                horario = "7:00 am",
                sala = "Sala 2",
                periodo = "Hoy",
                duracionMin = 60,
                cuposDisponibles = 5,
                cuposTotales = 20,
                descripcion = "Clase de yoga para mejorar movilidad y fuerza postural."
            ),
            ClaseFit(
                id = 2,
                titulo = "Cross Training",
                horario = "6:00 pm",
                sala = "Sala 1",
                periodo = "Hoy",
                duracionMin = 50,
                cuposDisponibles = 8,
                cuposTotales = 15,
                descripcion = "Entrenamiento funcional de alta intensidad por intervalos."
            ),
            ClaseFit(
                id = 3,
                titulo = "Spinning",
                horario = "7:30 pm",
                sala = "Sala 3",
                periodo = "Hoy",
                duracionMin = 45,
                cuposDisponibles = 12,
                cuposTotales = 25,
                descripcion = "Sesión cardiovascular en bicicleta estática con ritmo guiado."
            ),
            ClaseFit(
                id = 4,
                titulo = "Pilates Mat",
                horario = "8:00 am",
                sala = "Sala 2",
                periodo = "Esta semana",
                duracionMin = 60,
                cuposDisponibles = 6,
                cuposTotales = 18,
                descripcion = "Fortalecimiento de zona media, elasticidad y control corporal."
            ),
            ClaseFit(
                id = 5,
                titulo = "Boxeo Recreativo",
                horario = "5:00 pm",
                sala = "Sala 1",
                periodo = "Esta semana",
                duracionMin = 60,
                cuposDisponibles = 10,
                cuposTotales = 20,
                descripcion = "Fundamentos técnicos de boxeo, coordinación y trabajo aeróbico."
            ),
            ClaseFit(
                id = 6,
                titulo = "Calistenia",
                horario = "6:30 pm",
                sala = "Zona Exterior",
                periodo = "Esta semana",
                duracionMin = 55,
                cuposDisponibles = 7,
                cuposTotales = 15,
                descripcion = "Entrenamiento de fuerza y resistencia con peso corporal."
            )
        )
    }

    var textoBusqueda by remember { mutableStateOf("") }
    var filtroPeriodo by remember { mutableStateOf("Hoy") }
    var claseSeleccionadaDialogo by remember { mutableStateOf<ClaseFit?>(null) }

    val clasesFiltradas = remember(filtroPeriodo, textoBusqueda) {
        catalogoClases.filter { clase ->
            val coincidePeriodo = if (filtroPeriodo == "Hoy") {
                clase.periodo.equals("Hoy", ignoreCase = true)
            } else {
                true
            }

            val coincideTexto = if (textoBusqueda.isBlank()) {
                true
            } else {
                clase.titulo.contains(textoBusqueda.trim(), ignoreCase = true) ||
                        clase.sala.contains(textoBusqueda.trim(), ignoreCase = true) ||
                        clase.horario.contains(textoBusqueda.trim(), ignoreCase = true)
            }

            coincidePeriodo && coincideTexto
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp)
    ) {
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                shape = RoundedCornerShape(14.dp),
                color = VerdeInstitucional
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "TECSUP Fit",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Hola, Diego",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp
                    )
                }
            }
        }

        item {
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                placeholder = {
                    Text("Buscar clase o sala...", fontSize = 14.sp, color = Color.Gray)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    if (textoBusqueda.isNotEmpty()) {
                        IconButton(onClick = { textoBusqueda = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Limpiar búsqueda",
                                tint = Color.Gray,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = GrisBorde,
                    focusedBorderColor = VerdeInstitucional
                )
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (filtroPeriodo == "Hoy") {
                    Button(
                        onClick = { filtroPeriodo = "Hoy" },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeInstitucional),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp)
                    ) {
                        Text("Hoy", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    }
                } else {
                    OutlinedButton(
                        onClick = { filtroPeriodo = "Hoy" },
                        border = BorderStroke(1.dp, GrisBorde),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp)
                    ) {
                        Text("Hoy", fontSize = 13.sp, color = Color.DarkGray)
                    }
                }

                if (filtroPeriodo == "Esta semana") {
                    Button(
                        onClick = { filtroPeriodo = "Esta semana" },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeInstitucional),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp)
                    ) {
                        Text("Esta semana", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    }
                } else {
                    OutlinedButton(
                        onClick = { filtroPeriodo = "Esta semana" },
                        border = BorderStroke(1.dp, GrisBorde),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp)
                    ) {
                        Text("Esta semana", fontSize = 13.sp, color = Color.DarkGray)
                    }
                }
            }
        }

        item {
            Text(
                text = if (filtroPeriodo == "Hoy") "Clases disponibles hoy" else "Clases disponibles esta semana",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        if (clasesFiltradas.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.SearchOff,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No se encontraron clases para \"$textoBusqueda\"",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(onClick = {
                            textoBusqueda = ""
                            filtroPeriodo = "Esta semana"
                        }) {
                            Text("Ver todas las clases", color = VerdeInstitucional, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            items(clasesFiltradas) { clase ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .clickable { onSeleccionarClase(clase) },
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = GrisFondoCard),
                    border = BorderStroke(1.dp, GrisBorde.copy(alpha = 0.6f)),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(VerdeClaroIcono, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.CallMade,
                                contentDescription = null,
                                tint = VerdeInstitucional,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = clase.titulo,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFF1E293B)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${clase.horario} • ${clase.sala}",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }

    claseSeleccionadaDialogo?.let { clase ->
        AlertDialog(
            onDismissRequest = { claseSeleccionadaDialogo = null },
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
                TextButton(onClick = { claseSeleccionadaDialogo = null }) {
                    Text(text = "Cancelar", color = Color.Gray, fontSize = 13.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        claseSeleccionadaDialogo = null
                        onSeleccionarClase(clase)
                        Toast.makeText(
                            context,
                            "Reserva confirmada para ${clase.titulo}",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeInstitucional),
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