package com.muhammad.yumify.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.muhammad.yumify.R

@Immutable
enum class BottomNavItems(
    val route : Destinations,
    @get:StringRes val title : Int,
    val selectedIcon : Int,
    val unSelectedIcon : Int
){
    HOME(
        route = Destinations.HomeScreen,
        selectedIcon = R.drawable.ic_home_filled,
        unSelectedIcon = R.drawable.ic_home_outlined,
        title = R.string.home
    ),
    Search(
        route = Destinations.SearchScreen,
        selectedIcon = R.drawable.ic_search,
        unSelectedIcon = R.drawable.ic_search,
        title = R.string.search
    ),
    Favourite(
        route = Destinations.FavouriteScreen,
        selectedIcon = R.drawable.ic_favourite_filled,
        unSelectedIcon = R.drawable.ic_favourite_outlined,
        title = R.string.favourite
    )
}