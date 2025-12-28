package com.muhammad.yumify.presentation.screens.search

sealed interface SearchAction{
    data object GetPopularChoices : SearchAction
    data object GetNewRecipes : SearchAction
    data class OnSearchQueryChange(val query : String) : SearchAction
}