package com.faridchavez.lab05navegacion

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object List : Screen("list")
    data object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
    data object Profile : Screen("profile")
}
