package com.faridchavez.lab03registronotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.lab03registronotas.ui.theme.Lab03RegistroNotasTheme
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03RegistroNotasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegistroNotasScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RegistroNotasScreen(modifier: Modifier = Modifier) {
    var notaFundamentos by remember { mutableFloatStateOf(0f) }
    var notaPOO by remember { mutableFloatStateOf(0f) }
    var notaMoviles by remember { mutableFloatStateOf(0f) }
    var notaBD by remember { mutableFloatStateOf(0f) }

    var redondear by remember { mutableStateOf(false) }
    var confirmado by remember { mutableStateOf(false) }
    var mostrarResultado by remember { mutableStateOf(false) }

    val moradoHeader = Color(0xFF5E4B8B)
    val badgeFondo = Color(0xFFEDE7F6)
    val badgeTexto = Color(0xFF5E4B8B)
    val fondoDegradado = Brush.verticalGradient(
        colors = listOf(Color(0xFFEDE7F6), Color(0xFFF9F7FC), Color.White)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(fondoDegradado)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(moradoHeader)
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Registro de Notas",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Notas del ciclo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )
            Text(
                text = "Desliza para asignar cada nota (0 a 20)",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(14.dp))

            FilaCursoSlider(
                nombreCurso = "Fundamentos de Programación",
                pesoTexto = "(20%)",
                valor = notaFundamentos,
                onValueChange = {
                    notaFundamentos = it
                    mostrarResultado = false
                },
                colorPrimario = moradoHeader,
                badgeFondo = badgeFondo,
                badgeTexto = badgeTexto
            )

            FilaCursoSlider(
                nombreCurso = "Programación Orientada a Objetos",
                pesoTexto = "(25%)",
                valor = notaPOO,
                onValueChange = {
                    notaPOO = it
                    mostrarResultado = false
                },
                colorPrimario = moradoHeader,
                badgeFondo = badgeFondo,
                badgeTexto = badgeTexto
            )

            FilaCursoSlider(
                nombreCurso = "Programación en Móviles",
                pesoTexto = "(30%)",
                valor = notaMoviles,
                onValueChange = {
                    notaMoviles = it
                    mostrarResultado = false
                },
                colorPrimario = moradoHeader,
                badgeFondo = badgeFondo,
                badgeTexto = badgeTexto
            )

            FilaCursoSlider(
                nombreCurso = "Base de Datos",
                pesoTexto = "(25%)",
                valor = notaBD,
                onValueChange = {
                    notaBD = it
                    mostrarResultado = false
                },
                colorPrimario = moradoHeader,
                badgeFondo = badgeFondo,
                badgeTexto = badgeTexto
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Redondear promedio final",
                    fontSize = 14.sp,
                    color = Color(0xFF333333)
                )
                Switch(
                    checked = redondear,
                    onCheckedChange = { redondear = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = moradoHeader
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = confirmado,
                    onCheckedChange = { confirmado = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = moradoHeader
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Confirmo que las notas son correctas",
                    fontSize = 14.sp,
                    color = Color(0xFF333333)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { mostrarResultado = true },
                enabled = confirmado,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = moradoHeader,
                    disabledContainerColor = Color(0xFFBDB4D0),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "CALCULAR PROMEDIO",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mostrarResultado) {
                val pPonderado = (notaFundamentos * 0.20f) +
                        (notaPOO * 0.25f) +
                        (notaMoviles * 0.30f) +
                        (notaBD * 0.25f)

                val pFinalValor = if (redondear) pPonderado.roundToInt().toFloat() else pPonderado
                val pFinalTexto = if (redondear) "${pPonderado.roundToInt()}" else String.format(Locale.US, "%.2f", pPonderado)

                val (observacion, chipBgColor, chipTextColor) = when {
                    pFinalValor >= 17f -> Triple("EXCELENTE", Color(0xFFD1E7DD), Color(0xFF0F5132))
                    pFinalValor >= 13f -> Triple("APROBADO", Color(0xFFD1E7DD), Color(0xFF198754))
                    pFinalValor >= 10f -> Triple("EN RECUPERACIÓN", Color(0xFFFFF3CD), Color(0xFF856404))
                    else -> Triple("DESAPROBADO", Color(0xFFF8D7DA), Color(0xFF842029))
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row {
                            Text(text = "Promedio ponderado:  ", fontSize = 14.sp, color = Color(0xFF424242))
                            Text(
                                text = String.format(Locale.US, "%.2f", pPonderado),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF212121)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Promedio final:  ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = moradoHeader
                            )
                            Text(
                                text = pFinalTexto,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = moradoHeader
                            )
                        }

                        if (redondear) {
                            Text(
                                text = "(redondeado)",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = chipBgColor,
                            modifier = Modifier.border(
                                width = 1.dp,
                                color = chipTextColor.copy(alpha = 0.25f),
                                shape = RoundedCornerShape(16.dp)
                            )
                        ) {
                            Text(
                                text = observacion,
                                color = chipTextColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "✓ Promedio calculado correctamente",
                        color = Color(0xFF198754),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Asigna las notas y confirma para calcular",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Desarrollado por: Farid Chavez Campos",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FilaCursoSlider(
    nombreCurso: String,
    pesoTexto: String,
    valor: Float,
    onValueChange: (Float) -> Unit,
    colorPrimario: Color,
    badgeFondo: Color,
    badgeTexto: Color
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = nombreCurso,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = pesoTexto,
                    fontSize = 12.sp,
                    color = colorPrimario
                )
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = badgeFondo
            ) {
                Text(
                    text = "${valor.roundToInt()}",
                    color = badgeTexto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                )
            }
        }

        Slider(
            value = valor,
            onValueChange = { onValueChange(it.roundToInt().toFloat()) },
            valueRange = 0f..20f,
            steps = 19,
            colors = SliderDefaults.colors(
                thumbColor = colorPrimario,
                activeTrackColor = colorPrimario,
                inactiveTrackColor = Color(0xFFD6CEE5)
            )
        )
    }
}