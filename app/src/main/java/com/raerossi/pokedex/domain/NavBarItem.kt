package com.raerossi.pokedex.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raerossi.pokedex.R

sealed class NavBarItem(
    val route: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    @StringRes val labelRes: Int
) {
    object Home : NavBarItem(
        route = "home",
        unselectedIcon = R.drawable.ic_home,
        selectedIcon = R.drawable.ic_home_filled,
        labelRes = R.string.home
    )

    object Favorites : NavBarItem(
        route = "favorites",
        unselectedIcon = R.drawable.ic_star,
        selectedIcon = R.drawable.ic_star_filled,
        labelRes = R.string.favorites
    )
}