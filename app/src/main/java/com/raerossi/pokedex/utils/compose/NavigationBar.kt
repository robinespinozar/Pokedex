package com.raerossi.pokedex.utils.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raerossi.pokedex.domain.NavBarItem
import com.raerossi.pokedex.ui.theme.PokedexTheme
import com.raerossi.pokedex.ui.theme.primaryKeyColor

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    val navItems = listOf(
        NavBarItem.Home,
        NavBarItem.Favorites
    )

    var selectedNavItem by remember { mutableStateOf(navItems.first()) }

    BottomNavigation(
        modifier
            .height(74.dp)
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
                onClick = { selectedNavItem = navItem }
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

@Composable
@Preview(showBackground = true)
fun NavigationBarPreview() {
    PokedexTheme {
        NavigationBar()
    }
}