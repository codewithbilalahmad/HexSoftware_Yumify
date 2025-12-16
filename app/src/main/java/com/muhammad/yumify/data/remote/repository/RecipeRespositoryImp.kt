package com.muhammad.yumify.data.remote.repository

import com.muhammad.yumify.domain.model.CategoryRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.model.RecipeCategory
import com.muhammad.yumify.domain.network.RecipeNetwork
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.domain.utils.Result

class RecipeRespositoryImp(
    private val recipeNetwork: RecipeNetwork
) : RecipeRepository{
    override suspend fun getRecommendedRecipe(): Result<List<Recipe>> {
        return recipeNetwork.getRecommendedRecipe()
    }

    override suspend fun getRecipesOfWeek(): Result<List<Recipe>> {
        return recipeNetwork.getRecipesOfWeek()
    }

    override suspend fun getSearchRecipe(query: String): Result<List<Recipe>> {
        return recipeNetwork.getSearchRecipe(query)
    }

    override suspend fun getRecipesCategories(): Result<List<RecipeCategory>> {
        return recipeNetwork.getRecipesCategories()
    }

    override suspend fun getCategoriesRecipes(category: String): Result<List<CategoryRecipe>> {
        return recipeNetwork.getCategoriesRecipes(category)
    }
}