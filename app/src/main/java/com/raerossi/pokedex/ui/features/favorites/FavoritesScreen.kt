package com.raerossi.pokedex.ui.features.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel = hiltViewModel(),modifier : Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(color = Color.Green)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "FAVORITES SCREEN"
        )
    }
}