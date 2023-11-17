package com.raerossi.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.raerossi.pokedex.ui.features.home.HomeScreen
import com.raerossi.pokedex.ui.features.home.HomeViewModel
import com.raerossi.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTheme {
                SetStatusBarColor(colorStatusBar = Color(0xFFDC4E4E), colorNavigationBar = Color(0xFFEFF3F4))
                HomeScreen(homeViewModel)
            }
        }
    }
}

@Composable
fun SetStatusBarColor(colorStatusBar: Color, colorNavigationBar: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(colorStatusBar)
        systemUiController.setNavigationBarColor(colorNavigationBar)
    }
}