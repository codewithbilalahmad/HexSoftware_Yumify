package com.muhammad.yumify.data.local.repository

import com.muhammad.yumify.data.local.dao.FavouriteRecipeDao
import com.muhammad.yumify.data.local.mappers.toFavouriteRecipeEntity
import com.muhammad.yumify.data.local.mappers.toRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteRecipeRepositoryImp(
    private val favouriteRecipeDao: FavouriteRecipeDao
) : FavouriteRecipeRepository {
    override suspend fun insertFavouriteRecipe(recipe: Recipe) {
        favouriteRecipeDao.insertFavouriteRecipe(recipe.toFavouriteRecipeEntity())
    }

    override suspend fun deleteFavouriteRecipe(id: String) {
        favouriteRecipeDao.deleteFavouriteRecipe(id)
    }

    override fun getFavouriteRecipes(): Flow<List<Recipe>> {
        return favouriteRecipeDao.getFavouriteRecipes().map {entities ->
            entities.map { entity -> entity.toRecipe() }
        }
    }

    override fun getFavouriteRecipesIds(): Flow<List<String>> {
        return favouriteRecipeDao.getFavouriteRecipeIds()
    }
}