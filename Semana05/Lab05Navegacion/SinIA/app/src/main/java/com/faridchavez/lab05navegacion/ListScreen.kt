package com.faridchavez.lab05navegacion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val elementos = (1..10).toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de elementos") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(elementos) { elemento ->
                    ListItem(
                        headlineContent = {
                            Text("Elemento $elemento")
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(
                                Screen.Detail.createRoute(elemento)
                            )
                        }
                    )
                }
            }
        }
    }
}