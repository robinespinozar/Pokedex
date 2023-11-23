package com.raerossi.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raerossi.pokedex.domain.NavBarItem
import com.raerossi.pokedex.ui.features.favorites.FavoritesScreen
import com.raerossi.pokedex.ui.features.home.HomeScreen

@Composable
fun PokedexNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavBarItem.Home.route
    ) {
        composable(NavBarItem.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(NavBarItem.Favorites.route) {
            FavoritesScreen(modifier = modifier)
        }
    }
}