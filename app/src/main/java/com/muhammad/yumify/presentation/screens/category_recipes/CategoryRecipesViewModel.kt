package com.muhammad.yumify.presentation.screens.category_recipes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.domain.utils.onError
import com.muhammad.yumify.domain.utils.onSuccess
import com.muhammad.yumify.presentation.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryRecipesViewModel(
    saveStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
): ViewModel(){
    private val category = saveStateHandle.toRoute<Destinations.CategoryRecipesScreen>().category
    private val _state = MutableStateFlow(CategoryRecipeState())
    val state = _state.asStateFlow()
    init {
        onAction(CategoryRecipeAction.GetCategoryRecipes)
    }
    fun onAction(action: CategoryRecipeAction){
        when(action){
            CategoryRecipeAction.GetCategoryRecipes -> getCategoryRecipes()
            is CategoryRecipeAction.OnSearchQueryChange -> onSearchQueryChange(action.query)
        }
    }
    private fun onSearchQueryChange(query: String) {
        _state.update {
            it.copy(
                query = query,
                categoryRecipes = if (query.isNotEmpty()) {
                    _state.value.allCategoryRecipes.filter { category ->
                        category.name.contains(query, ignoreCase = true)
                    }
                } else {
                    state.value.allCategoryRecipes
                })
        }
    }

    private fun getCategoryRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isRecipesLoading = true) }
            recipeRepository.getCategoriesRecipes(category).onSuccess { data ->
                _state.update {
                    it.copy(
                        isRecipesLoading = false,
                        recipeError = null,
                        categoryRecipes = data, allCategoryRecipes = data
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        isRecipesLoading = false,
                        recipeError = error
                    )
                }
            }
        }
    }
}