package com.muhammad.yumify.data.local.mappers

import com.muhammad.yumify.data.local.entity.FavouriteRecipeEntity
import com.muhammad.yumify.data.local.entity.RecentViewedRecipeEntity
import com.muhammad.yumify.domain.model.Recipe

fun Recipe.toFavouriteRecipeEntity(): FavouriteRecipeEntity {
    return FavouriteRecipeEntity(
        id = id,
        name = name,
        area = area,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients
    )
}
fun Recipe.toRecentViewedRecipeEntity(): RecentViewedRecipeEntity {
    return RecentViewedRecipeEntity(
        id = id,
        name = name,
        area = area,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients
    )
}
fun FavouriteRecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        area = area,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients
    )
}
fun RecentViewedRecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        area = area,
        imageUrl = imageUrl,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients
    )
}
