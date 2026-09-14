package com.faridchavez.lab05manejoestadosia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faridchavez.lab05manejoestadosia.ui.theme.Lab05ManejoEstadosIATheme

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

    val listaTareas = remember {
        mutableStateListOf<String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de tareas - Tecsup",
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
                Text("Ingrese una tarea")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Button(
            onClick = {
                if (textoTarea.isNotBlank()) {
                    listaTareas.add(textoTarea)
                    textoTarea = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar tarea")
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

            items(listaTareas) { tarea ->

                Text(
                    text = tarea,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}