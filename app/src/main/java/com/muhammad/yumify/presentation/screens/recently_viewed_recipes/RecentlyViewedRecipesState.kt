package com.muhammad.yumify.presentation.screens.recently_viewed_recipes

import com.muhammad.yumify.domain.model.Recipe

data class RecentlyViewedRecipesState(
    val recentViewedRecipes : List<Recipe> = emptyList()
)
