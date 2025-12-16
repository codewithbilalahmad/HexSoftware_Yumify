package com.muhammad.yumify.data.local.mappers

import com.muhammad.yumify.data.local.entity.FavouriteRecipeEntity
import com.muhammad.yumify.data.local.entity.RecentViewedRecipeEntity
import com.muhammad.yumify.domain.model.Recipe

fun Recipe.toFavouriteRecipeEntity(): FavouriteRecipeEntity {
    return FavouriteRecipeEntity(
        id = id,
        name = name,
        area = area,
        imageUrl = imageUrl, timeStamp = timeStamp,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients, time = time, calories = calories
    )
}
fun Recipe.toRecentViewedRecipeEntity(isFavourite: Boolean): RecentViewedRecipeEntity {
    return RecentViewedRecipeEntity(
        id = id,
        name = name, timeStamp = timeStamp,
        area = area,
        imageUrl = imageUrl, isFavourite = isFavourite,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients, time = time, calories = calories
    )
}
fun FavouriteRecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        area = area, isFavourite = true,
        imageUrl = imageUrl, timeStamp = timeStamp,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags,
        youtubeLink = youtubeLink, ingredients = ingredients, time = time, calories = calories
    )
}
fun RecentViewedRecipeEntity.toRecipe(isFavourite : Boolean): Recipe {
    return Recipe(
        id = id,
        name = name,
        area = area,
        imageUrl = imageUrl, isFavourite = isFavourite,
        category = category,
        instructions = instructions,
        source = source,
        tags = tags, timeStamp = timeStamp,
        youtubeLink = youtubeLink, ingredients = ingredients
    )
}
