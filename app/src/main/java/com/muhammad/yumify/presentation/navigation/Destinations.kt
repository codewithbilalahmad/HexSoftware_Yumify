package com.muhammad.yumify.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object HomeScreen : Destinations
    @Serializable
    data object SearchScreen : Destinations
    @Serializable
    data object CategoriesScreen : Destinations
    @Serializable
    data class CategoryRecipesScreen(val category : String) : Destinations
    @Serializable
    data object FavouriteScreen : Destinations
    @Serializable
    data class RecipeDetailScreen(val id : String) : Destinations
}