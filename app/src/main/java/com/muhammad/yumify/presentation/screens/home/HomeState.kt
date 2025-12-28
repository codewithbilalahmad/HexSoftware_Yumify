package com.muhammad.yumify.presentation.screens.home

import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.model.RecipeCategory

data class HomeState(
    val recommendedRecipes : List<Recipe> = emptyList(),
    val isPopularRecipeLoading : Boolean = true,
    val popularRecipeError : String? = null,
    val popularRecipes : List<Recipe> = emptyList(),
    val recipesOfWeek : List<Recipe> = emptyList(),
    val categories : List<RecipeCategory> = emptyList(),
    val isRecommendedRecipeLoading : Boolean = true,
    val isRecipeOfWeekLoading : Boolean = true,
    val isCategoriesLoading : Boolean = true,
    val recommendedRecipeError : String? = null,
    val recipeOfWeekError : String? = null,
    val favouriteRecipesIds : List<String> = emptyList(),
    val categoriesError : String? = null
)
