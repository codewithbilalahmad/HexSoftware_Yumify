package com.muhammad.yumify.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryRecipeDto(
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name : String,
    @SerialName("strMealThumb")
    val imageUrl: String
)