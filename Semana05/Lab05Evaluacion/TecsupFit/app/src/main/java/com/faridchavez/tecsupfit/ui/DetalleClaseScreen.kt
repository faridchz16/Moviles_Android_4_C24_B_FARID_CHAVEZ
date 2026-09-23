package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.tecsupfit.model.ClaseFit
import com.faridchavez.tecsupfit.model.GrisTexto
import com.faridchavez.tecsupfit.model.VerdeFondoCard
import com.faridchavez.tecsupfit.model.VerdePrincipal

@Composable
fun DetalleClaseScreen(
    clase: ClaseFit,
    onVolver: () -> Unit,
    onReservar: (ClaseFit) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onVolver() }
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "← Detalle de clase",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(VerdeFondoCard),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = VerdePrincipal,
                modifier = Modifier.size(72.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = clase.titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${clase.horario} · ${clase.sala} · ${clase.duracionMin} min",
            fontSize = 13.sp,
            color = GrisTexto
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = clase.descripcion,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onReservar(clase) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdePrincipal,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Reservar cupo",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}