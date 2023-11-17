package com.raerossi.pokedex.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
//import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.pokedex.domain.Pokemon
import com.raerossi.pokedex.ui.theme.PokedexTheme
import com.raerossi.pokedex.utils.compose.NavigationBar
import com.raerossi.pokedex.utils.compose.SearchBar
import com.raerossi.pokedex.utils.compose.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading by homeViewModel.isLoading.observeAsState(false)

    Scaffold(
        topBar = { TopBar(text = "Pokedex") { } },
        bottomBar = { NavigationBar() }
    ) { padding ->
        if (isLoading) {
            LoadingScreen()
        } else {
            ScreenContent(
                homeViewModel = homeViewModel,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF2B292B))
    ) {
        SearchBar(Modifier.padding(all = 16.dp))
        PokemonList(
            modifier = Modifier.padding(horizontal = 16.dp),
            homeViewModel = homeViewModel
        )
    }
}

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
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    PokedexTheme {
        // HomeScreen(HomeViewModel())
    }
}