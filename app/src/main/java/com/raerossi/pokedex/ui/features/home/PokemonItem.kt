package com.raerossi.pokedex.ui.features.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raerossi.pokedex.domain.Pokemon
import com.raerossi.pokedex.ui.theme.PokedexTheme

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    homeViewModel: HomeViewModel,
    onClick: (Pokemon) -> Unit
) {
    Card(
        modifier
            .height(234.dp)
            .clickable { onClick(pokemon) },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE3E3E3))
    ) {
        CardContent(pokemon = pokemon, homeViewModel = homeViewModel)
    }
}

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    pokemon: Pokemon
) {
    Column(
        modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val id = pokemon.getId()
        var backgroundColor by remember { mutableStateOf(Color.White) }

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .height(181.dp)
                .padding(horizontal = 16.dp, vertical = 28.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.getImageUrl(id))
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "pokemon image",
            onSuccess = { success ->
                val drawable = success.result.drawable
                homeViewModel.getBackgroundColor(drawable) { color ->
                    backgroundColor = color
                }
            }
        )
        Divider(color = Color(0xFFE3E3E3), thickness = 1.dp)
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = pokemon.getformatId(id),
            color = Color(0xFF576B74),
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
            text = pokemon.name,
            color = Color(0xFF050C0E),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PokemonItemPreview() {
    PokedexTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF000000)), contentAlignment = Alignment.Center
        ) {
            // PokemonItem(Modifier.padding(16.dp))
        }
    }
}