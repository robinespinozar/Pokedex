package com.raerossi.pokedex.ui.features.pokemonlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raerossi.pokedex.ui.features.home.HomeViewModel

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    val pokemonList by homeViewModel.pokemonList.observeAsState(emptyList())

    LazyVerticalGrid(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                homeViewModel = homeViewModel
            ) {
                homeViewModel.openBottomSheet(it.getId())
            }
        }
    }
}