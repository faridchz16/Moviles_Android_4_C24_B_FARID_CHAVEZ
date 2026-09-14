package com.faridchavez.lab05manejoestadosia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faridchavez.lab05manejoestadosia.ui.theme.Lab05ManejoEstadosIATheme

data class Tarea(
    val id: Int,
    val nombre: String,
    val completada: Boolean = false
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab05ManejoEstadosIATheme {
                PantallaTareas()
            }
        }
    }
}

@Composable
fun PantallaTareas() {

    var textoTarea by remember {
        mutableStateOf("")
    }

    var siguienteId by remember {
        mutableStateOf(1)
    }

    val listaTareas = remember {
        mutableStateListOf<Tarea>()
    }

    val azulOscuro = Color(0xFF232C91)
    val fondo = Color(0xFFF3F6FB)
    val borde = Color(0xFF7766B5)
    val colorPapelera = Color(0xFF86C3CB)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(
                start = 22.dp,
                end = 22.dp,
                top = 48.dp
            )
    ) {

        Text(
            text = "Lista de tareas - Tecsup",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = azulOscuro
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = textoTarea,
            onValueChange = {
                textoTarea = it
            },
            label = {
                Text(
                    text = "¿Qué tarea tienes pendiente?",
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            singleLine = true,
            shape = RoundedCornerShape(3.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borde,
                unfocusedBorderColor = borde,
                focusedLabelColor = borde,
                unfocusedLabelColor = borde,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(
            onClick = {

                if (textoTarea.isNotBlank()) {

                    listaTareas.add(
                        Tarea(
                            id = siguienteId,
                            nombre = textoTarea.trim()
                        )
                    )

                    siguienteId++
                    textoTarea = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = azulOscuro
            )
        ) {

            Text(
                text = "Agregar tarea",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Total de tareas: ${listaTareas.size}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color(0xFF333333)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = listaTareas,
                key = {
                    it.id
                }
            ) { tarea ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(9.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 10.dp,
                                end = 8.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = tarea.completada,
                            onCheckedChange = { marcada ->

                                val posicion =
                                    listaTareas.indexOfFirst {
                                        it.id == tarea.id
                                    }

                                if (posicion != -1) {

                                    listaTareas[posicion] =
                                        tarea.copy(
                                            completada = marcada
                                        )
                                }
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = azulOscuro
                            )
                        )

                        Text(
                            text = tarea.nombre,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp),
                            fontSize = 16.sp,
                            color = Color(0xFF333333),
                            textDecoration =
                                if (tarea.completada) {
                                    TextDecoration.LineThrough
                                } else {
                                    TextDecoration.None
                                }
                        )

                        IconButton(
                            onClick = {
                                listaTareas.remove(tarea)
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Eliminar tarea",
                                tint = colorPapelera
                            )
                        }
                    }
                }
            }
        }
    }
}