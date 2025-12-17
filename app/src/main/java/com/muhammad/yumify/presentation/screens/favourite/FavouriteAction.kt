package com.muhammad.yumify.presentation.screens.favourite

sealed interface FavouriteAction{
    data class OnSearchQueryChange(val text : String) : FavouriteAction
}