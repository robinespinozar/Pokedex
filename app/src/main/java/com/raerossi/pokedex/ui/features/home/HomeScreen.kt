package com.raerossi.pokedex.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.raerossi.pokedex.R
import com.raerossi.pokedex.data.remote.model.Pokemon
import com.raerossi.pokedex.ui.features.detail.DetailBottomSheet
import com.raerossi.pokedex.ui.features.pokemonlist.PokemonList
import com.raerossi.pokedex.ui.features.utils.LoadingScreen
import com.raerossi.pokedex.ui.theme.onSearchBarContainer
import com.raerossi.pokedex.ui.theme.searchBarContainer

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val isLoading by homeViewModel.isLoading.observeAsState(false)
    val pokemonList by homeViewModel.pokemonList.observeAsState(emptyList())

    if (isLoading) {
        LoadingScreen()
    } else {
        ScreenContent(pokemonList = pokemonList, modifier = modifier)
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    pokemonList: List<Pokemon>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF2B292B))
    ) {
        SearchBar()
        PokemonList(pokemonList = pokemonList)
        DetailBottomSheet()
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }

    TextField(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
            .border(
                width = 0.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(24.dp)
            ),
        value = name,
        onValueChange = { name = it },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true,
        enabled = true,
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.padding(start = 6.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search bar",
                    tint = MaterialTheme.colorScheme.onSearchBarContainer
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onSearchBarContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSearchBarContainer,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSearchBarContainer,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSearchBarContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.searchBarContainer,
            focusedContainerColor = MaterialTheme.colorScheme.searchBarContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "Search...",
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}
