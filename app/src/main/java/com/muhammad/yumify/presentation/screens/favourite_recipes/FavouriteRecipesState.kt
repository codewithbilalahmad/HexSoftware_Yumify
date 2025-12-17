package com.muhammad.yumify.presentation.screens.favourite_recipes

import com.muhammad.yumify.domain.model.Recipe

data class FavouriteRecipesState(
    val favouriteRecipes : List<Recipe> = emptyList()
)