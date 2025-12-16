package com.muhammad.yumify.presentation.screens.categories

sealed interface CategoriesAction{
    data object GetRecipeCategories : CategoriesAction
    data class OnSearchQueryChange(val text : String) : CategoriesAction
}