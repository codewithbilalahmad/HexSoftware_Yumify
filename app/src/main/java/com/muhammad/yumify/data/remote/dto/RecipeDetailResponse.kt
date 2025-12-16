package com.muhammad.yumify.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailResponse(
    @SerialName("meals")
    val recipes : List<RecipeDto>
)
