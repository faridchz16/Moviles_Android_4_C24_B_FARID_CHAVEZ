package com.faridchavez.lab05navegacion

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    itemId: Int,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detalle del elemento")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        val colorFlecha = MaterialTheme.colorScheme.onSurface

                        Canvas(modifier = Modifier.size(24.dp)) {
                            val grosor = 2.dp.toPx()
                            val centroY = size.height / 2f

                            drawLine(
                                color = colorFlecha,
                                start = Offset(size.width * 0.15f, centroY),
                                end = Offset(size.width * 0.85f, centroY),
                                strokeWidth = grosor,
                                cap = StrokeCap.Round
                            )

                            drawLine(
                                color = colorFlecha,
                                start = Offset(size.width * 0.15f, centroY),
                                end = Offset(size.width * 0.45f, size.height * 0.2f),
                                strokeWidth = grosor,
                                cap = StrokeCap.Round
                            )

                            drawLine(
                                color = colorFlecha,
                                start = Offset(size.width * 0.15f, centroY),
                                end = Offset(size.width * 0.45f, size.height * 0.8f),
                                strokeWidth = grosor,
                                cap = StrokeCap.Round
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Elemento #$itemId",
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "ID recibido: $itemId",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Este valor llegó como argumento tipado Int desde el NavHost."
                    )
                }
            }
        }
    }
}