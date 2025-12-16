package com.muhammad.yumify.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class CategoryRecipe(
    val id : String,
    val name : String,
    val imageUrl : String,
)