package com.raerossi.pokedex.ui.features.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.raerossi.pokedex.R
import com.raerossi.pokedex.domain.NavBarItem
import com.raerossi.pokedex.ui.navigation.PokedexNavHost
import com.raerossi.pokedex.ui.features.main.NavigationBar
import com.raerossi.pokedex.ui.features.main.TopBar
import com.raerossi.pokedex.ui.theme.primaryKeyColor
import com.raerossi.pokedex.utils.extensions.navigationShadow
import com.raerossi.pokedex.utils.extensions.smallShadow

@Composable
fun MainScreen() {
    SetStatusBarColor(colorStatusBar = Color(0xFFDC4E4E), colorNavigationBar = Color(0xFFEFF3F4))
    val activity = LocalContext.current as Activity
    var title by remember { mutableStateOf("Pokedex") }
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(text = title) { activity.finish() } },
        bottomBar = {
            NavigationBar {
                navController.navigate(it.route)
                {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
                title = it.title
            }
        }
    ) { padding ->
        PokedexNavHost(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier, onNavItemClick: (NavBarItem) -> Unit) {
    val navItems = listOf(
        NavBarItem.Home,
        NavBarItem.Favorites
    )

    var selectedNavItem by remember { mutableStateOf(navItems.first()) }

    BottomNavigation(
        modifier
            .height(74.dp)
            .navigationShadow()
            .background(color = Color(0xFF2B292B))
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        backgroundColor = MaterialTheme.colorScheme.primaryKeyColor
    ) {
        navItems.forEach { navItem ->
            BottomNavigationItem(
                selected = navItem == selectedNavItem,
                icon = {
                    IconItem(
                        isSelected = navItem == selectedNavItem,
                        selectedIcon = navItem.selectedIcon,
                        unselectedIcon = navItem.unselectedIcon
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                },
                label = {
                    LabelItem(
                        isSelected = navItem == selectedNavItem,
                        labelRes = navItem.labelRes
                    )
                },
                onClick = {
                    selectedNavItem = navItem
                    onNavItemClick(selectedNavItem)
                }
            )
        }
    }
}

@Composable
private fun IconItem(
    isSelected: Boolean,
    @DrawableRes selectedIcon: Int,
    @DrawableRes unselectedIcon: Int
) {
    Icon(
        painter = if (isSelected) painterResource(id = selectedIcon) else painterResource(id = unselectedIcon),
        tint = if (isSelected) Color.White else Color(0xFFFAFAFA),
        contentDescription = "nav icon"
    )
}

@Composable
private fun LabelItem(
    isSelected: Boolean,
    @StringRes labelRes: Int
) {
    Text(
        text = stringResource(id = labelRes),
        color = if (isSelected) Color.White else Color(0xFFFAFAFA),
        style = MaterialTheme.typography.labelSmall
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier
            .smallShadow()
            .height(48.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryKeyColor,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = { TitleText(text) },
        navigationIcon = { NavigationIcon { onBackClick() } }
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
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "back arrow"
            )
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
