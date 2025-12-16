package com.muhammad.yumify.presentation.screens.recipe_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.muhammad.yumify.presentation.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipeDetailViewModel(
    saveStateHandle: SavedStateHandle
) : ViewModel(){
    private val recipe = saveStateHandle.toRoute<Destinations.RecipeDetailScreen>().recipe
    private val _state = MutableStateFlow(RecipeDetailsState())
    val state = _state.asStateFlow()
    init {
        onAction(RecipeDetailAction.GetRecipeDetails)
    }
    fun onAction(action: RecipeDetailAction){
        when(action){
            RecipeDetailAction.GetRecipeDetails -> getRecipeDetails()
            RecipeDetailAction.OnRecipeFavouriteToggle -> TODO()
        }
    }
    private fun getRecipeDetails(){
        _state.update { it.copy(recipeDetails = recipe) }
    }
}