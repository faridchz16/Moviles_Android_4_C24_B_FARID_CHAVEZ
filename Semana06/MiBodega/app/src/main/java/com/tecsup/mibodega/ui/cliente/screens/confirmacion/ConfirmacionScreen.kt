package com.tecsup.mibodega.ui.cliente.screens.confirmacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tecsup.mibodega.ui.componentes.BotonPrimario
import com.tecsup.mibodega.ui.theme.GrisClaro
import com.tecsup.mibodega.ui.theme.RojoPrecio
import com.tecsup.mibodega.ui.theme.VerdeBodega

@Composable
fun ConfirmacionScreen(
    onVolverInicio: () -> Unit,
    onVerEstadoPedido: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(80.dp)
                .background(VerdeBodega, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(44.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "¡Pedido realizado!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = VerdeBodega
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Tu pedido está siendo preparado\ny será entregado pronto",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = GrisClaro)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Pedido #1024",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        text = "S/ 25.90",
                        fontWeight = FontWeight.Bold,
                        color = RojoPrecio
                    )
                }

                Spacer(Modifier.height(8.dp))
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f))
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Dirección",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Av. Los Olivos 123\n(Frente al parque)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.weight(1f))

        OutlinedButton(
            onClick = onVerEstadoPedido,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(VerdeBodega)
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = null,
                tint = VerdeBodega,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(text = "Ver estado del pedido", color = VerdeBodega, fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(12.dp))

        BotonPrimario(
            texto = "Volver al inicio",
            onClick = onVolverInicio
        )

        Spacer(Modifier.height(24.dp))
    }
}