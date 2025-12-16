package com.muhammad.yumify.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.muhammad.yumify.utils.createBeautifulColor

@Immutable
data class RecipeCategory(
    val id : String,
    val name : String,
    val imageUrl : String,
    val description : String,
    val color : Color = createBeautifulColor()
)
