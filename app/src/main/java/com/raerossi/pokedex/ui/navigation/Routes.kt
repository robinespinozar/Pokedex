package com.raerossi.pokedex.ui.navigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes(route = "home")
    object FavoritesScreen : Routes(route = "favorites")
}