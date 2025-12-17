package com.muhammad.yumify.presentation.screens.recipe_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import com.muhammad.yumify.domain.repository.RecentViewedRecipeRepository
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.domain.utils.onError
import com.muhammad.yumify.domain.utils.onSuccess
import com.muhammad.yumify.presentation.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    saveStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
    private val favouriteRecipeRepository: FavouriteRecipeRepository,
    private val recentViewedRecipeRepository: RecentViewedRecipeRepository,
) : ViewModel() {
    private val id = saveStateHandle.toRoute<Destinations.RecipeDetailScreen>().id
    private val _state = MutableStateFlow(RecipeDetailsState())
    val state = _state.asStateFlow()

    init {
        onAction(RecipeDetailAction.GetRecipeDetails)
    }

    fun onAction(action: RecipeDetailAction) {
        when (action) {
            RecipeDetailAction.GetRecipeDetails -> getRecipeDetails()
            RecipeDetailAction.OnRecipeFavouriteToggle -> onRecipeFavouriteToggle()
        }
    }

    private fun onRecipeFavouriteToggle() {
        viewModelScope.launch {
            val recipe = state.value.recipeDetails ?: return@launch
            if (recipe.isFavourite) {
                favouriteRecipeRepository.deleteFavouriteRecipe(recipe.id)
            } else {
                favouriteRecipeRepository.insertFavouriteRecipe(recipe = recipe)
            }
            _state.update { it.copy(recipeDetails = recipe.copy(isFavourite = !recipe.isFavourite)) }
        }
    }

    private fun getRecipeDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isDetailLoading = true) }
            recipeRepository.getRecipeDetail(id).onSuccess { data ->
                _state.update {
                    it.copy(
                        isDetailLoading = false,
                        recipeDetailError = null,
                        recipeDetails = data
                    )
                }
                recentViewedRecipeRepository.insertRecentViewedRecipe(recipe = data)
            }.onError { error ->
                _state.update {
                    it.copy(
                        isDetailLoading = false,
                        recipeDetailError = error
                    )
                }
            }
        }
    }
}