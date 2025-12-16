package com.muhammad.yumify.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class RecipeIngredient(
    val name : String,
    val measure : String
)
