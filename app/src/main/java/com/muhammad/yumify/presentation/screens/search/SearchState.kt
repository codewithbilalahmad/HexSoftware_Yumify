package com.muhammad.yumify.presentation.screens.search

import com.muhammad.yumify.domain.model.Recipe

data class SearchState(
    val searchQuery : String = "",
    val newRecipeError : String?=null,
    val newRecipeLoading : Boolean = false,
    val newRecipes : List<Recipe> = emptyList(),
    val popularChoiceLoading : Boolean = false,
    val popularChoiceError : String?=null,
    val searchRecipes : List<Recipe> = emptyList(),
    val popularChoices: List<Recipe> = emptyList(),
    val isSearchLoading : Boolean = false,
    val searchError : String?=null
)
