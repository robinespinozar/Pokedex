package com.raerossi.pokedex.ui.features.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raerossi.pokedex.R
import com.raerossi.pokedex.data.remote.model.PokemonDetail
import com.raerossi.pokedex.data.remote.model.Types
import com.raerossi.pokedex.ui.theme.PokedexTheme
import com.raerossi.pokedex.ui.theme.bottomSheetContainer
import com.raerossi.pokedex.ui.theme.onBottomSheetContainer
import com.raerossi.pokedex.ui.theme.primaryKeyColor
import com.raerossi.pokedex.utils.PokemonTypeUtils
import com.raerossi.pokedex.utils.extensions.largeShadow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBottomSheet(
    detailViewModel: DetailViewModel = hiltViewModel(),
    onReloadScreen: () -> Unit
) {
    val isSheetOpen by detailViewModel.isSheetOpen.observeAsState(false)
    val isSheetLoading by detailViewModel.isSheetLoading.observeAsState(false)
    val detail by detailViewModel.detail.observeAsState()
    val isFavorite by detailViewModel.isFavorite.observeAsState(false)
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = Color(0xFF1C1B1E),
            onDismissRequest = {
                detailViewModel.hideBottomSheet()
                onReloadScreen()
            },
            sheetState = sheetState,
            dragHandle = {
                DragHandle { BottomSheetDefaults.DragHandle(color = Color(0xFF4C494F)) }
            }
        ) {
            ModalBottomSheetContent(
                detail = detail,
                isFavorite = isFavorite,
                isSheetLoading = isSheetLoading,
                onHideClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            detailViewModel.hideBottomSheet()
                            onReloadScreen()
                        }
                    }
                },
                onFavoriteClick = { detailViewModel.updateFavoritePokemon(it, detail!!.id) }
            )
        }
    }
}

@Composable
fun DragHandle(setDragHandle: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        setDragHandle()
    }
}

@Composable
fun ModalBottomSheetContent(
    detail: PokemonDetail?,
    isFavorite: Boolean,
    isSheetLoading: Boolean,
    onHideClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    if (isSheetLoading) {
        LoadingSheet()
    } else {
        SheetContent(
            detail = detail,
            isFavorite = isFavorite,
            onHideClick = { onHideClick() },
            onFavoriteClick = { onFavoriteClick(it) }
        )
    }
}

@Composable
fun SheetContent(
    detail: PokemonDetail?,
    isFavorite: Boolean,
    onHideClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = MaterialTheme.colorScheme.bottomSheetContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonImage(
            imageUrl = detail!!.sprite.other.oficialArt.imagen,
            isFavorite = isFavorite,
            onHideClick = { onHideClick() },
            onFavoriteClick = { onFavoriteClick(it) }
        )
        HeaderDetails(detail!!.getIdFormat(), detail!!.name)
        TypeDetails(typeList = getTypes(detail!!.types))
        PhysicalDetails(detail!!.getHeightFormat(), detail!!.getWeightFormat())
        StatsDetails(detail = detail!!)
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
                .align(Alignment.TopCenter), color = MaterialTheme.colorScheme.primaryKeyColor
        )
    }
}

@Composable
fun HeaderDetails(number: String, name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.onBottomSheetContainer,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun PhysicalDetails(height: String, weight: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        PhysicalAttribute(
            icon = R.drawable.ic_height,
            text = height,
            label = "height"
        )
        Spacer(modifier = Modifier.width(36.dp))
        PhysicalAttribute(
            icon = R.drawable.ic_weight,
            text = weight,
            label = "weight"
        )
    }
}

@Composable
fun StatsDetails(modifier: Modifier = Modifier, detail: PokemonDetail) {
    Text(
        modifier = Modifier.padding(top = 32.dp, bottom = 8.dp),
        text = "STATS",
        style = MaterialTheme.typography.labelLarge,
        color = Color.White
    )
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        StatsLabels()
        Spacer(modifier = Modifier.width(32.dp))
        StatsIndicators(detail = detail)
    }
}

@Composable
fun StatsLabels() {
    Column(
        modifier = Modifier.height(96.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StatLabel("HP")
        StatLabel("ATK")
        StatLabel("DEF")
        StatLabel("SPD")
    }
}

@Composable
fun StatsIndicators(detail: PokemonDetail) {
    Column(
        modifier = Modifier
            .height(96.dp)
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StatsLinearIndicator(detail.getHpValue(), Color(0xFFDC4E4E))
        StatsLinearIndicator(detail.getAttackValue(), Color(0xFF2C44CC))
        StatsLinearIndicator(detail.getDefenseValue(), Color(0xFF4DBB9B))
        StatsLinearIndicator(detail.getSpeedValue(), Color(0xFFFBC02D))
    }
}

fun getTypes(types: List<Types>): List<String> {
    return types.map {
        val tpyeName = it.type.name
        tpyeName
    }
}

@Composable
fun StatLabel(title: String) {
    Text(
        text = title,
        color = Color.White,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
fun StatsLinearIndicator(value: Float, colorIndicator: Color) {
    Card(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .height(10.dp)
                .width(250.dp),
            progress = value,
            color = colorIndicator,
            trackColor = Color(0xFFEFF3F4)
        )
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
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(id = icon),
            contentDescription = "physic attribute",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
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
    isFavorite: Boolean,
    imageUrl: String,
    onHideClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    Column(
        modifier
            .largeShadow()
            .fillMaxWidth()
    ) {
        SheetBar(
            isFavorite = isFavorite,
            onHideClick = { onHideClick() },
            onFavoriteClick = { onFavoriteClick(it) }
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(200.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "pokemon image"
        )
    }
}

@Composable
fun SheetBar(
    isFavorite: Boolean,
    onHideClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    //var isFavoriteState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.padding(start = 8.dp),
            onClick = { onHideClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = "close button",
                tint = Color.Unspecified
            )
        }
        IconButton(
            modifier = Modifier.padding(end = 8.dp),
            onClick = {
                //isFavoriteState = !isFavorite
                onFavoriteClick(!isFavorite)
            }) {
            Icon(
                painter = if (isFavorite) painterResource(id = R.drawable.ic_favorite_star_filled) else painterResource(
                    id = R.drawable.ic_favorite_star
                ),
                contentDescription = "favorite button",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun TypeDetails(typeList: List<String>) {
    LazyRow(
        modifier = Modifier.padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(typeList) { type ->
            TypeItem(type)
        }
    }
}

@Composable
fun TypeItem(type: String) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(32.dp)
            .clip(shape = RoundedCornerShape(36.dp))
            .background(PokemonTypeUtils.getTypeColor(type))
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = type,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
fun StatsDetailsPreview() {
    PokedexTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF000000)),
            contentAlignment = Alignment.Center
        ) {
            //TypeDetails()
        }
    }
}