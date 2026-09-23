package com.faridchavez.tecsupfit.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.model.ClaseFit

val clasesHoy = listOf(
    ClaseFit(1, "Yoga funcional", "7:00 am · Sala 2"),
    ClaseFit(2, "Cross Training", "6:00 pm · Sala 1"),
    ClaseFit(3, "Spinning", "7:30 pm · Sala 3")
)

val clasesSemana = listOf(
    ClaseFit(1, "Yoga funcional", "7:00 am · Sala 2"),
    ClaseFit(2, "Cross Training", "6:00 pm · Sala 1"),
    ClaseFit(3, "Spinning", "7:30 pm · Sala 3"),
    ClaseFit(4, "Pilates Mat", "Mañana 8:00 am · Sala 2"),
    ClaseFit(5, "Boxeo Recreativo", "Jueves 5:00 pm · Sala 1"),
    ClaseFit(6, "Calistenia", "Viernes 6:30 pm · Zona Exterior")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onSeleccionarClase: (ClaseFit) -> Unit) {
    val context = LocalContext.current
    var busqueda by remember { mutableStateOf("") }
    var filtroTiempo by remember { mutableStateOf("HOY") }
    var claseAReservar by remember { mutableStateOf<ClaseFit?>(null) }

    val listaBase = if (filtroTiempo == "HOY") clasesHoy else clasesSemana

    val clasesFiltradas = listaBase.filter { clase ->
        clase.nombre.contains(busqueda, ignoreCase = true) ||
                clase.horario.contains(busqueda, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00695C))
                .padding(20.dp)
        ) {
            Column {
                Text("TECSUP Fit", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Hola, Diego", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            placeholder = { Text("Buscar clase o sala...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00695C),
                cursorColor = Color(0xFF00695C)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Button(
                onClick = { filtroTiempo = "HOY" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filtroTiempo == "HOY") Color(0xFF00695C) else Color.Transparent,
                    contentColor = if (filtroTiempo == "HOY") Color.White else Color.Gray
                ),
                shape = RoundedCornerShape(20.dp),
                border = if (filtroTiempo != "HOY") ButtonDefaults.outlinedButtonBorder else null,
                modifier = Modifier.height(36.dp)
            ) {
                Text("Hoy", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { filtroTiempo = "SEMANA" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filtroTiempo == "SEMANA") Color(0xFF00695C) else Color.Transparent,
                    contentColor = if (filtroTiempo == "SEMANA") Color.White else Color.Gray
                ),
                shape = RoundedCornerShape(20.dp),
                border = if (filtroTiempo != "SEMANA") ButtonDefaults.outlinedButtonBorder else null,
                modifier = Modifier.height(36.dp)
            ) {
                Text("Esta semana", fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (filtroTiempo == "HOY") "Clases disponibles hoy" else "Clases disponibles esta semana",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(clasesFiltradas) { clase ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { claseAReservar = clase },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FitnessCenter,
                            contentDescription = null,
                            tint = Color(0xFF00695C),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = clase.nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(text = clase.horario, color = Color.Gray, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }

    // Diálogo de confirmación de reserva
    claseAReservar?.let { clase ->
        AlertDialog(
            onDismissRequest = { claseAReservar = null },
            title = {
                Text("Confirmar reserva", fontWeight = FontWeight.Bold)
            },
            text = {
                Column {
                    Text(text = "¿Deseas reservar la siguiente clase?", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Clase: ${clase.nombre}", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                    Text(text = "Horario y Sala: ${clase.horario}", color = Color.Gray, fontSize = 13.sp)
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Reserva confirmada para ${clase.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()
                        onSeleccionarClase(clase)
                        claseAReservar = null
                    }
                ) {
                    Text("Confirmar reserva", color = Color(0xFF00695C), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { claseAReservar = null }
                ) {
                    Text("Cancelar", color = Color.Gray)
                }
            }
        )
    }
}
