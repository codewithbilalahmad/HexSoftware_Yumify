package com.muhammad.yumify.presentation.screens.category_recipes

import com.muhammad.yumify.domain.model.CategoryRecipe

data class CategoryRecipeState(
    val categoryRecipes : List<CategoryRecipe> = emptyList(),
    val allCategoryRecipes : List<CategoryRecipe> = emptyList(),
    val isRecipesLoading : Boolean = false,
    val recipeError : String?=null,
    val query : String = "",
)
