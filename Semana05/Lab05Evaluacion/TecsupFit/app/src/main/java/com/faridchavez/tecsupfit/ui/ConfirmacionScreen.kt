package com.faridchavez.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
fun ConfirmacionScreen(
    clase: ClaseFit?,
    onIrAMisReservas: () -> Unit
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
                .size(80.dp)
                .clip(CircleShape)
                .background(VerdeFondoCard),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = VerdePrincipal,
                modifier = Modifier.size(44.dp)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "¡Cupo reservado!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = clase?.titulo ?: "Clase seleccionada",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${clase?.periodo ?: "Hoy"}, ${clase?.horario ?: ""} · ${clase?.sala ?: ""}",
            fontSize = 12.sp,
            color = GrisTexto
        )

        Spacer(modifier = Modifier.height(36.dp))

        Button(
            onClick = { onIrAMisReservas() },
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(44.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEEEEEE),
                contentColor = Color.DarkGray
            )
        ) {
            Text(
                text = "Ver mis reservas",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}