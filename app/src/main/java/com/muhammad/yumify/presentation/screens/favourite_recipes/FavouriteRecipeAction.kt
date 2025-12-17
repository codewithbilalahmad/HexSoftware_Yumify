package com.muhammad.yumify.presentation.screens.favourite_recipes

sealed interface FavouriteRecipeAction{
    data class OnUnFavouriteRecipe(val id : String) : FavouriteRecipeAction
}