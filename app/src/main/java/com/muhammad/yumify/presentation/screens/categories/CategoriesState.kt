package com.muhammad.yumify.presentation.screens.categories

import com.muhammad.yumify.domain.model.RecipeCategory

data class CategoriesState(
    val query : String = "",
    val categories: List<RecipeCategory> = emptyList(),
    val allCategories: List<RecipeCategory> = emptyList(),
    val isCategoriesLoading: Boolean = false,
    val categoriesError: String? = null
)