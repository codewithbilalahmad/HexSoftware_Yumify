package com.muhammad.yumify.data.local.repository

import com.muhammad.yumify.data.local.dao.FavouriteRecipeDao
import com.muhammad.yumify.data.local.dao.RecentViewedRecipeDao
import com.muhammad.yumify.data.local.mappers.toRecentViewedRecipeEntity
import com.muhammad.yumify.data.local.mappers.toRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.repository.RecentViewedRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecentViewedRecipeRepositoryImp(
    private val recentViewedRecipeDao: RecentViewedRecipeDao,
    private val favouriteRecipeDao: FavouriteRecipeDao
) : RecentViewedRecipeRepository{
    override suspend fun insertRecentViewedRecipe(recipe: Recipe) {
        val isFavourite = favouriteRecipeDao.getFavouriteRecipe(recipe.id) != null
        recentViewedRecipeDao.insertRecentViewedRecipe(recipe.toRecentViewedRecipeEntity(isFavourite))
    }

    override suspend fun deleteRecentViewedRecipe(id: String) {
        recentViewedRecipeDao.deleteRecentViewedRecipe(id)
    }

    override suspend fun getRecentViewedRecipe(id: String): Flow<Recipe>? {
        val isFavourite = favouriteRecipeDao.getFavouriteRecipe(id) != null
        return recentViewedRecipeDao.getRecentViewedRecipe(id)?.map {entity -> entity.toRecipe(isFavourite) }
    }

    override fun getRecentViewedRecipes(): Flow<List<Recipe>> {
        return recentViewedRecipeDao.getRecentViewedRecipes().map {entities ->
            entities.map { entity ->
                val isFavourite = favouriteRecipeDao.getFavouriteRecipe(entity.id) != null
                entity.toRecipe(isFavourite = isFavourite)
            }
        }
    }
}