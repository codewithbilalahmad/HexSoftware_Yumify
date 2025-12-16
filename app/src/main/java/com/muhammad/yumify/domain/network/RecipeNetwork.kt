package com.muhammad.yumify.domain.network

import com.muhammad.yumify.domain.model.CategoryRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.model.RecipeCategory
import com.muhammad.yumify.domain.utils.Result

interface RecipeNetwork{
    suspend fun getRecommendedRecipe() : Result<List<Recipe>>
    suspend fun getRecipesOfWeek() : Result<List<Recipe>>
    suspend fun getSearchRecipe(query : String) : Result<List<Recipe>>
    suspend fun getRecipesCategories() : Result<List<RecipeCategory>>
    suspend fun getCategoriesRecipes(category : String) : Result<List<CategoryRecipe>>
}