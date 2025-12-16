package com.muhammad.yumify.domain.repository

import com.muhammad.yumify.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface FavouriteRecipeRepository {
    suspend fun insertFavouriteRecipe(recipe: Recipe)
    suspend fun deleteFavouriteRecipe(id : String)
    fun getFavouriteRecipes() : Flow<List<Recipe>>
    fun getFavouriteRecipesIds() : Flow<List<String>>
}