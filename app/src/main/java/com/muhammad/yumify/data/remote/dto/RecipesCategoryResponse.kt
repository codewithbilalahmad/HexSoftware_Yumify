package com.muhammad.yumify.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesCategoryResponse(
    @SerialName("categories")
    val categories : List<RecipeCategoryDto>
)
