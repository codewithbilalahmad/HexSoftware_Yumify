package com.muhammad.yumify.data.remote.mappers

import com.muhammad.yumify.data.remote.dto.CategoryRecipeDto
import com.muhammad.yumify.data.remote.dto.RecipeCategoryDto
import com.muhammad.yumify.data.remote.dto.RecipeDto
import com.muhammad.yumify.domain.model.CategoryRecipe
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.domain.model.RecipeCategory
import com.muhammad.yumify.domain.model.RecipeIngredient

fun RecipeDto.getIngredients(): List<RecipeIngredient> {
    val ingredients = listOf(
        ingredient1 to measure1,
        ingredient2 to measure2,
        ingredient3 to measure3,
        ingredient4 to measure4,
        ingredient5 to measure5,
        ingredient6 to measure6,
        ingredient7 to measure7,
        ingredient8 to measure8,
        ingredient9 to measure9,
        ingredient10 to measure10,
        ingredient11 to measure11,
        ingredient12 to measure12,
        ingredient13 to measure13,
        ingredient14 to measure14,
        ingredient15 to measure15,
        ingredient16 to measure16,
        ingredient17 to measure17,
        ingredient18 to measure18,
        ingredient19 to measure19,
        ingredient20 to measure20
    )
    return ingredients.filter { (name, _) ->
        !name.isNullOrBlank()
    }.map { (name, measure) -> RecipeIngredient(name.orEmpty(), measure.orEmpty()) }
}

fun RecipeDto.toRecipe(isFavourite : Boolean): Recipe {
    return Recipe(
        id = id.orEmpty(),
        name = name.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        area = area.orEmpty(),
        category = category.orEmpty(),
        instructions = instructions.orEmpty(),
        tags = tags.orEmpty(),timeStamp = System.currentTimeMillis(),
        youtubeLink = youtubeLink.orEmpty(), isFavourite = isFavourite,
        source = source.orEmpty(), ingredients = getIngredients()
    )
}

fun RecipeCategoryDto.toCategory(): RecipeCategory {
    return RecipeCategory(id = id, name = name, imageUrl = imageUrl, description = description)
}

fun CategoryRecipeDto.toCategoryRecipe(): CategoryRecipe {
    return CategoryRecipe(id = id, name = name, imageUrl = imageUrl)
}