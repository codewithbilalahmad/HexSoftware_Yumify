package com.muhammad.yumify.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.domain.utils.onError
import com.muhammad.yumify.domain.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val favouriteRecipeRepository: FavouriteRecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = combine(
        _state, favouriteRecipeRepository.getFavouriteRecipesIds()
    ) { state, favouriteRecipeIds ->
        state.copy(
            favouriteRecipesIds = favouriteRecipeIds,
            recommendedRecipes = state.recommendedRecipes.map { recipe ->
                recipe.copy(isFavourite = recipe.id in favouriteRecipeIds)
            },
            recipesOfWeek = state.recipesOfWeek.map { recipe ->
                recipe.copy(isFavourite = recipe.id in favouriteRecipeIds)
            })
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), HomeState())

    init {
        onAction(HomeAction.GetRecipeCategories)
        onAction(HomeAction.GetRecommendedRecipes)
        onAction(HomeAction.GetRecipesOfWeek)
    }

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.GetRecipeCategories -> getRecipeCategories()
            HomeAction.GetRecipesOfWeek -> getRecipesOfWeek()
            HomeAction.GetRecommendedRecipes -> getRecommendedRecipes()
            is HomeAction.OnRecipeFavouriteToggle -> onRecipeFavouriteToggle(action.recipe)
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

    private fun getRecipesOfWeek() {
        viewModelScope.launch {
            _state.update { it.copy(isRecipeOfWeekLoading = true) }
            recipeRepository.getRecipesOfWeek().onSuccess { data ->
                _state.update {
                    it.copy(
                        isRecipeOfWeekLoading = false,
                        recommendedRecipeError = null,
                        recipesOfWeek = data
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isRecipeOfWeekLoading = false,
                        recipeOfWeekError = error
                    )
                }
            }
        }
    }

    private fun getRecommendedRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isRecommendedRecipeLoading = true) }
            recipeRepository.getRecommendedRecipe().onSuccess { data ->
                _state.update {
                    it.copy(
                        isRecommendedRecipeLoading = false,
                        recommendedRecipeError = null,
                        recommendedRecipes = data
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isRecommendedRecipeLoading = false,
                        recommendedRecipeError = error
                    )
                }
            }
        }
    }

    private fun getRecipeCategories() {
        viewModelScope.launch {
            _state.update { it.copy(isCategoriesLoading = true) }
            recipeRepository.getRecipesCategories().onSuccess { data ->
                _state.update {
                    it.copy(
                        isCategoriesLoading = false,
                        categoriesError = null,
                        categories = data.take(8)
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isCategoriesLoading = false,
                        categoriesError = error
                    )
                }
            }
        }
    }
}