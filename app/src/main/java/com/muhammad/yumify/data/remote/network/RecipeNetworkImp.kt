package com.muhammad.yumify.data.remote.network

import com.muhammad.yumify.data.remote.dto.CategoryRecipesResponse
import com.muhammad.yumify.data.remote.dto.RecipesCategoryResponse
import com.muhammad.yumify.data.remote.dto.RecipesResponse
import com.muhammad.yumify.data.remote.mappers.toCategory
import com.muhammad.yumify.data.remote.mappers.toCategoryRecipe
import com.muhammad.yumify.data.remote.mappers.toRecipe
import com.muhammad.yumify.data.remote.network.client.get
import com.muhammad.yumify.domain.model.CategoryRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.model.RecipeCategory
import com.muhammad.yumify.domain.network.RecipeNetwork
import com.muhammad.yumify.domain.utils.Result
import com.muhammad.yumify.domain.utils.map
import io.ktor.client.HttpClient

class RecipeNetworkImp(
    private val httpClient: HttpClient,
) : RecipeNetwork {
    override suspend fun getRecommendedRecipe(): Result<List<Recipe>> {
        return httpClient.get<RecipesResponse>(
            "search.php", queryParameters = mapOf(
                "f" to "a"
            )
        ).map { data ->
            data.recipes.map {recipe ->
                recipe.toRecipe()
            }
        }
    }

    override suspend fun getRecipesOfWeek(): Result<List<Recipe>> {
        return httpClient.get<RecipesResponse>(
            "search.php", queryParameters = mapOf(
                "f" to "b"
            )
        ).map { data ->
            data.recipes.map {recipe ->
                recipe.toRecipe()
            }
        }
    }

    override suspend fun getSearchRecipe(query: String): Result<List<Recipe>> {
        return httpClient.get<RecipesResponse>(
            "search.php", queryParameters = mapOf(
                "s" to query
            )
        ).map { data ->
            data.recipes.map {recipe ->
                recipe.toRecipe()
            }
        }
    }

    override suspend fun getRecipesCategories(): Result<List<RecipeCategory>> {
        return httpClient.get<RecipesCategoryResponse>(
            "categories.php"
        ).map { data ->
            data.categories.map {category ->
                category.toCategory()
            }
        }
    }

    override suspend fun getCategoriesRecipes(category: String): Result<List<CategoryRecipe>> {
        return httpClient.get<CategoryRecipesResponse>(
            "filter.php", queryParameters = mapOf(
                "c" to category
            )
        ).map { data ->
            data.recipes.map {recipe ->
                recipe.toCategoryRecipe()
            }
        }
    }
}