package com.faridchavez.lab05manejoestadosia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de tareas - Tecsup",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Total de tareas: 0"
        )
    }
}