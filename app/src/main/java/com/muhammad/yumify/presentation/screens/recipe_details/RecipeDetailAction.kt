package com.muhammad.yumify.presentation.screens.recipe_details

sealed interface RecipeDetailAction {
    data object GetRecipeDetails : RecipeDetailAction
    data object OnRecipeFavouriteToggle : RecipeDetailAction
}