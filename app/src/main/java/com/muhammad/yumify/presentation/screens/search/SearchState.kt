package com.muhammad.yumify.presentation.screens.search

import com.muhammad.yumify.domain.model.Recipe

data class SearchState(
    val searchQuery : String = "",
    val searchRecipes : List<Recipe> = emptyList(),
    val isSearchLoading : Boolean = false,
    val searchError : String?=null
)
