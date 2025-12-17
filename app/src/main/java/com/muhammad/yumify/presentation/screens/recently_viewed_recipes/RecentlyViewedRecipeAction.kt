package com.muhammad.yumify.presentation.screens.recently_viewed_recipes

import com.muhammad.yumify.domain.model.Recipe

sealed interface RecentlyViewedRecipeAction{
    data class OnRecipeFavouriteToggle(val recipe : Recipe) : RecentlyViewedRecipeAction
}