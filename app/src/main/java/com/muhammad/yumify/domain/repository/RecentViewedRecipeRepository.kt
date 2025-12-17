package com.muhammad.yumify.domain.repository

import com.muhammad.yumify.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecentViewedRecipeRepository {
    suspend fun insertRecentViewedRecipe(recipe: Recipe)
    suspend fun deleteRecentViewedRecipe(id : String)
    suspend fun getRecentViewedRecipe(id : String) : Flow<Recipe>?
    fun getRecentViewedRecipes() : Flow<List<Recipe>>
}