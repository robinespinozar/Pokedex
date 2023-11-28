package com.raerossi.pokedex.ui.features.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.pokedex.data.remote.model.Pokemon
import com.raerossi.pokedex.ui.features.detail.DetailBottomSheet
import com.raerossi.pokedex.ui.features.pokemonlist.PokemonList
import com.raerossi.pokedex.ui.features.utils.LoadingScreen

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val isLoading by favoritesViewModel.isLoading.observeAsState(false)
    val favoritesPokemonList by favoritesViewModel.favoritesPokemonList.observeAsState(emptyList())

    if (isLoading) {
        LoadingScreen()
    } else {
        ScreenContent(
            favoritesPokemonList = favoritesPokemonList,
            modifier = modifier
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    favoritesPokemonList: List<Pokemon>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF2B292B))
    ) {
        PokemonList(pokemonList = favoritesPokemonList)
        DetailBottomSheet()
    }
}