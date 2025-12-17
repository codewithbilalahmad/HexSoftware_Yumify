package com.muhammad.yumify.presentation.screens.recently_viewed_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import com.muhammad.yumify.domain.repository.RecentViewedRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecentlyViewedRecipesViewModel(
    private val recentViewedRecipeRepository: RecentViewedRecipeRepository,
    private val favouriteRecipeRepository: FavouriteRecipeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RecentlyViewedRecipesState())
    val state = combine(
        _state, recentViewedRecipeRepository.getRecentViewedRecipes()
    ) { state, recentViewedRecipes ->
        state.copy(recentViewedRecipes = recentViewedRecipes)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), RecentlyViewedRecipesState())

    fun onAction(action : RecentlyViewedRecipeAction){
        when(action){
           is  RecentlyViewedRecipeAction.OnRecipeFavouriteToggle -> onRecipeFavouriteToggle(action.recipe)
        }
    }

    private fun onRecipeFavouriteToggle(recipe: Recipe) {
        viewModelScope.launch {
            if(recipe.isFavourite){
                favouriteRecipeRepository.deleteFavouriteRecipe(recipe.id)
            } else{
                favouriteRecipeRepository.insertFavouriteRecipe(recipe)
            }
        }
    }
}