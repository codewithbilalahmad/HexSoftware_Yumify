package com.muhammad.yumify.presentation.screens.home

import com.muhammad.yumify.domain.model.Recipe

sealed interface HomeAction {
    data object GetRecommendedRecipes : HomeAction
    data object GetRecipeCategories : HomeAction
    data object GetPopularRecipes : HomeAction
    data object GetRecipesOfWeek : HomeAction
    data class OnRecipeFavouriteToggle(val recipe : Recipe) : HomeAction
}