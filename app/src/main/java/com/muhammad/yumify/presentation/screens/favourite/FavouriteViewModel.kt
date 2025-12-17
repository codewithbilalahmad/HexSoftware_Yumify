package com.muhammad.yumify.presentation.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import com.muhammad.yumify.domain.repository.RecentViewedRecipeRepository
import com.muhammad.yumify.presentation.screens.favourite.FavouriteAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FavouriteViewModel(
    private val favouriteRecipeRepository: FavouriteRecipeRepository,
    private val recentViewedRecipeRepository: RecentViewedRecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(FavouriteState())
    val state = combine(
        _state,
        favouriteRecipeRepository.getFavouriteRecipes(),
        recentViewedRecipeRepository.getRecentViewedRecipes()
    ) { state, favouriteRecipes, recentViewedRecipes ->
        state.copy(favouriteRecipes = favouriteRecipes, recentViewedRecipes = recentViewedRecipes)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), FavouriteState())

    fun onAction(action: FavouriteAction){
        when(action){
            is FavouriteAction.OnSearchQueryChange -> onSearchQueryChange(action.text)
        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }
}