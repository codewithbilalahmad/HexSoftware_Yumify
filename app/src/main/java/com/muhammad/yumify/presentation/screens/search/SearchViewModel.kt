package com.muhammad.yumify.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.domain.utils.onError
import com.muhammad.yumify.domain.utils.onSuccess
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()
    private var searchJob: Job? = null

    init {
        onAction(SearchAction.GetPopularChoices)
        onAction(SearchAction.GetNewRecipes)
        observeSearchQuery()
    }

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchQueryChange -> onSearchQueryChange(action.query)
            SearchAction.GetPopularChoices -> getPopularChoices()
            SearchAction.GetNewRecipes -> getNewRecipes()
        }
    }

    private fun getNewRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(newRecipeLoading = true) }
            recipeRepository.getNewRecipes().onSuccess { data ->
                _state.update {
                    it.copy(
                        newRecipeLoading = false,
                        newRecipeError = null,
                        newRecipes = data
                    )
                }
            }.onError { error ->
                _state.update { it.copy(newRecipeLoading = false, newRecipeError = error) }
            }
        }
    }

    private fun getPopularChoices() {
        viewModelScope.launch {
            _state.update { it.copy(popularChoiceLoading = true) }
            recipeRepository.getPopularChoices().onSuccess { data ->
                _state.update {
                    it.copy(
                        popularChoiceLoading = false,
                        popularChoiceError = null,
                        popularChoices = data
                    )
                }
            }.onError { error ->
                _state.update { it.copy(popularChoiceLoading = false, popularChoiceError = error) }
            }
        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            state.map { it.searchQuery }.debounce(500).distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        _state.update {
                            it.copy(
                                isSearchLoading = false,
                                searchRecipes = emptyList(),
                                searchError = null
                            )
                        }
                    } else {
                        searchJob?.cancel()
                        searchJob = getSearchRecipes(query)
                    }
                }
        }
    }

    private fun getSearchRecipes(query: String) = viewModelScope.launch {
        _state.update { it.copy(isSearchLoading = true) }
        recipeRepository.getSearchRecipe(query).onSuccess { data ->
            _state.update {
                it.copy(
                    isSearchLoading = false,
                    searchError = null,
                    searchRecipes = data
                )
            }
        }.onError { error ->
            _state.update { it.copy(isSearchLoading = false, searchError = error) }
        }
    }
}