package com.faridchavez.lab05manejoestados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import com.faridchavez.lab05manejoestados.ui.theme.Lab05ManejoEstadosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab05ManejoEstadosTheme {
                PantallaTareas()
            }
        }
    }
}

@Composable
fun ContadorRoto() {

    var contador = 0

    Column {
        Text("Contador: $contador")

        Button(
            onClick = {
                contador++
            }
        ) {
            Text("Incrementar")
        }
    }
}

@Composable
fun ContadorConRemember() {

    var contador by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Contador: $contador",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = {
                contador++
            }
        ) {
            Text("Incrementar")
        }
    }
}

@Composable
fun TemperatureDisplay() {

    var temperatura by remember {
        mutableStateOf(20)
    }

    val colorTemperatura = when {
        temperatura > 30 -> Color.Red
        temperatura < 10 -> Color.Blue
        else -> Color.Unspecified
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Temperatura: $temperatura °C",
            style = MaterialTheme.typography.headlineMedium,
            color = colorTemperatura
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row {

            Button(
                onClick = {
                    temperatura++
                }
            ) {
                Text("Subir")
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Button(
                onClick = {
                    temperatura--
                }
            ) {
                Text("Bajar")
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = {
                temperatura = 20
            }
        ) {
            Text("Resetear")
        }
    }
}

data class Tarea(
    val id: Int,
    val nombre: String,
    val completada: Boolean = false
)

@Composable
fun ItemTarea(
    tarea: Tarea,
    onEliminar: () -> Unit,
    onCambiarEstado: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = tarea.completada,
                onCheckedChange = onCambiarEstado
            )

            Text(
                text = tarea.nombre,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onEliminar
            ) {
                Text("Eliminar")
            }
        }
    }
}

@Composable
fun PantallaTareas() {

    var textoTarea by remember {
        mutableStateOf("")
    }

    var contadorId by remember {
        mutableStateOf(1)
    }

    val listaTareas = remember {
        mutableStateListOf<Tarea>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Control de Tareas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = textoTarea,
            onValueChange = {
                textoTarea = it
            },
            label = {
                Text("Nueva tarea")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = {
                if (textoTarea.isNotBlank()) {

                    listaTareas.add(
                        Tarea(
                            id = contadorId,
                            nombre = textoTarea
                        )
                    )

                    contadorId++
                    textoTarea = ""
                }
            }
        ) {
            Text("Agregar")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Total de tareas: ${listaTareas.size}"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyColumn {

            items(
                items = listaTareas,
                key = {
                    it.id
                }
            ) { tarea ->

                ItemTarea(
                    tarea = tarea,

                    onEliminar = {
                        listaTareas.remove(tarea)
                    },

                    onCambiarEstado = { completada ->

                        val indice = listaTareas.indexOfFirst {
                            it.id == tarea.id
                        }

                        if (indice != -1) {
                            listaTareas[indice] = tarea.copy(
                                completada = completada
                            )
                        }
                    }
                )
            }
        }
    }
}