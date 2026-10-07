package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.model.ClaseFit

private val VerdeTecsup = Color(0xFF0D634C)

@Composable
fun ReservasScreen(
    reservas: List<ClaseFit> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8F7))
            .padding(16.dp)
    ) {
        Text(
            text = "Mis reservas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Muestra las clases que has ido reservando en la sesión
            items(reservas) { clase ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = clase.titulo,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "${clase.periodo}, ${clase.horario} • ${clase.sala}",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Confirmada",
                            fontSize = 12.sp,
                            color = VerdeTecsup,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Clases base que venían de ejemplo
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Cross Training", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Hoy, 6:00 pm • Box Principal", fontSize = 13.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Confirmada", fontSize = 12.sp, color = VerdeTecsup, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Yoga funcional", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("Hoy, 7:00 am • Sala C", fontSize = 13.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Completada", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}