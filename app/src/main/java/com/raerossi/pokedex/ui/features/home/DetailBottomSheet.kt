package com.raerossi.pokedex.ui.features.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raerossi.pokedex.R
import com.raerossi.pokedex.domain.PokemonDetail
import com.raerossi.pokedex.ui.theme.PokedexTheme
import com.raerossi.pokedex.ui.theme.bottomSheetContainer
import com.raerossi.pokedex.ui.theme.onBottomSheetContainer
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBottomSheet(homeViewModel: HomeViewModel) {
    val detail by homeViewModel.detail.observeAsState()
    val isSheetOpen by homeViewModel.isSheetOpen.observeAsState(false)
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = Color(0xFF141010),
            onDismissRequest = { homeViewModel.hideBottomSheet() },
            sheetState = sheetState,
            dragHandle = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BottomSheetDefaults.DragHandle()
                    Divider(color = Color(0xFF655C5D))
                }
            }
        ) {
            val isSheetLoading by homeViewModel.isSheetLoading.observeAsState(false)
            if (isSheetLoading) {
                LoadingSheet()
            } else {
                BottomSheetContent(
                    detail = detail,
                    homeViewModel = homeViewModel,
                    onCloseClickButton = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                homeViewModel.hideBottomSheet()
                            }
                        }
                    },
                    onFavoriteClickButton = {}
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    detail: PokemonDetail?,
    homeViewModel: HomeViewModel,
    onCloseClickButton: () -> Unit,
    onFavoriteClickButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.bottomSheetContainer)
    ) {
        PokemonImage(
            imageUrl = detail!!.sprite.other.oficialArt.imagen,
            homeViewModel = homeViewModel,
            onCloseClickButton = { onCloseClickButton() },
            onFavoriteClickButton = { onFavoriteClickButton() }
        )
        if (detail != null) {
            HeaderDetails(detail = detail)
            PhysicalDetails(detail = detail)
            StatsDetails()
        }
    }
}

@Composable
private fun LoadingSheet(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.bottomSheetContainer),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            Modifier
                .padding(top = 32.dp)
                .align(Alignment.TopCenter), color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun HeaderDetails(modifier: Modifier = Modifier, detail: PokemonDetail) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = detail.getIdFormat(),
            color = MaterialTheme.colorScheme.onBottomSheetContainer,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = detail.name,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun PhysicalDetails(modifier: Modifier = Modifier, detail: PokemonDetail) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PhysicalAttribute(
            icon = R.drawable.ic_height,
            text = detail.getHeightFormat(),
            label = "height"
        )
        Spacer(modifier = Modifier.width(36.dp))
        PhysicalAttribute(
            icon = R.drawable.ic_weight,
            text = detail.getWeightFormat(),
            label = "weight"
        )
    }
}

@Composable
fun StatsDetails(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        StatsAttribute("HP", 0.7f, Color(0xFFDC4E4E))
        StatsAttribute("ATK", 1.0f, Color(0xFF2C44CC))
        StatsAttribute("DEF", 0.8f, Color(0xFF4DBB9B))
        StatsAttribute("SPD", 0.45f, Color(0xFFFBC02D))
    }
}

@Composable
fun StatsAttribute(title: String, value: Float, colorIndicator: Color) {
    Row(Modifier.padding(vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.width(24.dp))
        Card(
            modifier = Modifier.background(
                color = Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .height(10.dp)
                    .width(175.dp),
                progress = value,
                color = colorIndicator,
                trackColor = Color(0xFFEFF3F4)
            )
        }
    }
}

@Composable
fun PhysicalAttribute(
    @DrawableRes icon: Int,
    text: String,
    label: String
) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "physic attribute",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onBottomSheetContainer,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    homeViewModel: HomeViewModel,
    onCloseClickButton: () -> Unit,
    onFavoriteClickButton: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var backgroundColor by remember { mutableStateOf(Color.Transparent) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(backgroundColor), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var isFavorite by rememberSaveable { mutableStateOf(false) }

            IconButton(
                modifier = Modifier.padding(start = 8.dp),
                onClick = { onCloseClickButton() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_down_arrow),
                    contentDescription = "close button",
                    tint = Color.Unspecified
                )
            }
            IconButton(
                modifier = Modifier.padding(end = 8.dp),
                onClick = { isFavorite = !isFavorite }) {
                Icon(
                    painter = if (isFavorite) painterResource(id = R.drawable.ic_favorite_star_filled) else painterResource(
                        id = R.drawable.ic_favorite_star
                    ),
                    contentDescription = "favorite button",
                    tint = Color.Unspecified
                )
            }
        }
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .height(200.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
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
    }
}

@Composable
@Preview(showBackground = true)
fun StatsDetailsPreview() {
    PokedexTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF000000)),
            contentAlignment = Alignment.Center
        ) {
            StatsDetails()
        }
    }
}