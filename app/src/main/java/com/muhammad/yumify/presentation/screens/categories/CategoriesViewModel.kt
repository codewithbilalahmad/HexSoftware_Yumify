package com.muhammad.yumify.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.domain.utils.onError
import com.muhammad.yumify.domain.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow()

    init {
        onAction(CategoriesAction.GetRecipeCategories)
    }

    fun onAction(action: CategoriesAction) {
        when (action) {
            CategoriesAction.GetRecipeCategories -> getRecipeCategories()
            is CategoriesAction.OnSearchQueryChange -> onSearchQueryChange(action.text)
        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.update {
            it.copy(
                query = query,
                categories = if (query.isNotEmpty()) {
                    _state.value.allCategories.filter { category ->
                        category.name.contains(query, ignoreCase = true)
                    }
                } else {
                    state.value.allCategories
                })
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
                        categories = data, allCategories = data
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