package com.faridchavez.lab05navegacion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de inicio")

        Button(
            onClick = {
                navController.navigate(Screen.List.route)
            }
        ) {
            Text(text = "Ir a Lista")
        }

        Button(
            onClick = {
                navController.navigate(Screen.Profile.route)
            }
        ) {
            Text(text = "Ir a Perfil")
        }
    }
}