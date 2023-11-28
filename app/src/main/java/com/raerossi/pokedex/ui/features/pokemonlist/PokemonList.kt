package com.raerossi.pokedex.ui.features.pokemonlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.pokedex.data.remote.model.Pokemon
import com.raerossi.pokedex.ui.features.detail.DetailViewModel

@Composable
fun PokemonList(
    detailViewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    pokemonList: List<Pokemon>
) {
    LazyVerticalGrid(
        modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(pokemon = pokemon) {
                detailViewModel.openBottomSheet(it.getId())
            }
        }
    }
}