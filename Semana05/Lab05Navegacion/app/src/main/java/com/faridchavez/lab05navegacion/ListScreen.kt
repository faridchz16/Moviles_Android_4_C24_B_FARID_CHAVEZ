package com.faridchavez.lab05navegacion

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val elementos = (1..8).toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Lista")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(elementos) { elemento ->
                ListItem(
                    headlineContent = {
                        Text("Elemento número $elemento")
                    },
                    supportingContent = {
                        Text("Toca para ver el detalle")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.Detail.createRoute(elemento)
                            )
                        }
                )

                HorizontalDivider()
            }
        }
    }
}