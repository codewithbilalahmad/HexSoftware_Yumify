package com.muhammad.yumify.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecipeCategoryDto(
    @SerialName("idCategory")
    val id : String,
    @SerialName("strCategory")
    val name : String,
    @SerialName("strCategoryThumb")
    val imageUrl : String,
    @SerialName("strCategoryDescription")
    val description : String
)
