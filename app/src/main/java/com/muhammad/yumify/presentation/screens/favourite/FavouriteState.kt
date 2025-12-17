package com.muhammad.yumify.presentation.screens.favourite

import com.muhammad.yumify.domain.model.Recipe

data class FavouriteState(
    val favouriteRecipes: List<Recipe> = emptyList(),
    val recentViewedRecipes: List<Recipe> = emptyList(),
    val searchQuery : String = ""
)