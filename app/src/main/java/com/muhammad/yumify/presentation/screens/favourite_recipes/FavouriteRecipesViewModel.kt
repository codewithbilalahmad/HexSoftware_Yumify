package com.muhammad.yumify.presentation.screens.favourite_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavouriteRecipesViewModel(
    private val favouriteRecipeRepository: FavouriteRecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(FavouriteRecipesState())
    val state = combine(
        _state, favouriteRecipeRepository.getFavouriteRecipes()
    ) { state, favouriteRecipes ->
        state.copy(favouriteRecipes = favouriteRecipes)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), FavouriteRecipesState())

    fun onAction(action: FavouriteRecipeAction) {
        when(action){
            is FavouriteRecipeAction.OnUnFavouriteRecipe -> onUnFavouriteRecipe(action.id)
        }
    }

    private fun onUnFavouriteRecipe(id: String) {
        viewModelScope.launch {
            favouriteRecipeRepository.deleteFavouriteRecipe(id)
        }
    }
}