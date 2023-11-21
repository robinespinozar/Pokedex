package com.raerossi.pokedex.utils.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raerossi.pokedex.R
import com.raerossi.pokedex.ui.theme.PokedexTheme
import com.raerossi.pokedex.ui.theme.primaryKeyColor
import com.raerossi.pokedex.utils.extensions.largeShadow
import com.raerossi.pokedex.utils.extensions.mediumShadow
import com.raerossi.pokedex.utils.extensions.shadow
import com.raerossi.pokedex.utils.extensions.smallShadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    onBackClick : () -> Unit
) {
    TopAppBar(
        modifier = modifier.smallShadow().height(48.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryKeyColor,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = { TitleText(text) },
        navigationIcon = { NavigationIcon{ onBackClick() } }
    )
}

@Composable
private fun TitleText(text: String) {
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun NavigationIcon(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "back arrow"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview() {
    PokedexTheme {
        TopBar(text = "Pokedex"){}
    }
}