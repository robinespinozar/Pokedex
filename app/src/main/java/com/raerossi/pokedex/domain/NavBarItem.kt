package com.raerossi.pokedex.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raerossi.pokedex.R

sealed class NavBarItem(
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    @StringRes val labelRes: Int
) {
    object Home: NavBarItem(
        unselectedIcon = R.drawable.ic_home,
        selectedIcon = R.drawable.ic_home_filled,
        labelRes = R.string.home
    )
    object Favorites: NavBarItem(
        unselectedIcon = R.drawable.ic_star,
        selectedIcon = R.drawable.ic_star_filled,
        labelRes = R.string.favorites
    )
}