package com.muhammad.yumify.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    @SerialName("meals")
    val recipes: List<RecipeDto>
)