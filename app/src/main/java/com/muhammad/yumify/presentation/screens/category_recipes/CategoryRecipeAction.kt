package com.muhammad.yumify.presentation.screens.category_recipes

sealed interface CategoryRecipeAction{
    data object GetCategoryRecipes : CategoryRecipeAction
    data class OnSearchQueryChange(val query : String) : CategoryRecipeAction
}