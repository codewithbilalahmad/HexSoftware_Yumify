package com.muhammad.yumify.presentation.screens.search

sealed interface SearchAction{
    data class OnSearchQueryChange(val query : String) : SearchAction
}