package com.muhammad.yumify.data.local.repository

import com.muhammad.yumify.data.local.dao.RecentViewedRecipeDao
import com.muhammad.yumify.data.local.mappers.toRecentViewedRecipeEntity
import com.muhammad.yumify.data.local.mappers.toRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.repository.RecentViewedRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecentViewedRecipeRepositoryImp(
    private val recentViewedRecipeDao: RecentViewedRecipeDao
) : RecentViewedRecipeRepository{
    override suspend fun insertRecentViewedRecipe(recipe: Recipe) {
        recentViewedRecipeDao.insertRecentViewedRecipe(recipe.toRecentViewedRecipeEntity())
    }

    override suspend fun deleteRecentViewedRecipe(id: String) {
        recentViewedRecipeDao.deleteRecentViewedRecipe(id)
    }

    override fun getRecentViewedRecipes(): Flow<List<Recipe>> {
        return recentViewedRecipeDao.getRecentViewedRecipes().map {entities ->
            entities.map { entity -> entity.toRecipe() }
        }
    }
}