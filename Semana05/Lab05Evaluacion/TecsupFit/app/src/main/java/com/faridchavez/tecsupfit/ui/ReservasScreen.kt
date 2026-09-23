package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.data.misReservasGlobales
import com.faridchavez.tecsupfit.model.GrisTexto
import com.faridchavez.tecsupfit.model.VerdeAcento
import com.faridchavez.tecsupfit.model.VerdePrincipal

@Composable
fun ReservasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Text(
            text = "Mis reservas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(misReservasGlobales) { reserva ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F9F8)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .width(5.dp)
                                .height(68.dp)
                                .background(if (reserva.estado == "Confirmada") VerdePrincipal else Color.LightGray)
                        )
                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Text(
                                text = reserva.clase.titulo,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${reserva.clase.periodo}, ${reserva.clase.horario}",
                                fontSize = 12.sp,
                                color = GrisTexto
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = reserva.estado,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (reserva.estado == "Confirmada") VerdeAcento else GrisTexto
                            )
                        }
                    }
                }
            }
        }
    }
}