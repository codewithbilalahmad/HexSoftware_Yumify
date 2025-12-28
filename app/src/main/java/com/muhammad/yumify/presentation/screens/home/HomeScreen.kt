package com.muhammad.yumify.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.muhammad.yumify.presentation.navigation.AppBottomBar
import com.muhammad.yumify.presentation.navigation.Destinations
import com.muhammad.yumify.presentation.screens.home.components.HomeCategoriesSection
import com.muhammad.yumify.presentation.screens.home.components.HomeHeader
import com.muhammad.yumify.presentation.screens.home.components.RecipesOfWeekSection
import com.muhammad.yumify.presentation.screens.home.components.RecommendedRecipesSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {
    val layoutDirection = LocalLayoutDirection.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                AppBottomBar(
                    navController = navHostController
                )
            }
        }, contentWindowInsets = WindowInsets.waterfall,
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + 16.dp,
                start = paddingValues.calculateStartPadding(layoutDirection),
                end = paddingValues.calculateEndPadding(layoutDirection)
            ), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "HomeHeader") {
                HomeHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(), onSearchClick = {
                    }, isPopularRecipeLoading = state.isPopularRecipeLoading,
                    popularRecipeError = state.popularRecipeError,
                    popularRecipes = state.popularRecipes, onPopularRecipeClick = {})
            }
            item("HomeCategoriesSection") {
                HomeCategoriesSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    categories = state.categories,
                    onSeeAllCategories = {
                        navHostController.navigate(Destinations.CategoriesScreen)
                    },
                    categoriesError = state.categoriesError,
                    isCategoriesLoading = state.isCategoriesLoading,
                    onCategoryClick = { category ->
                        navHostController.navigate(Destinations.CategoryRecipesScreen(category.name))
                    })
            }
            item("RecommendedRecipesSection") {
                RecommendedRecipesSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    onSeeAllRecommendedRecipes = {},
                    isRecommendedRecipesLoading = state.isRecommendedRecipeLoading,
                    recommendedRecipeError = state.recommendedRecipeError,
                    recommendedRecipes = state.recommendedRecipes, onRecipeFavouriteToggle = {recipe ->
                        viewModel.onAction(HomeAction.OnRecipeFavouriteToggle(recipe))
                    }, onRecipeClick = {recipe ->
                        navHostController.navigate(Destinations.RecipeDetailScreen(recipe.id))
                    }
                )
            }
            item("RecipesOfWeekSection") {
                RecipesOfWeekSection(
                    modifier = Modifier.fillMaxWidth(),
                    recipesOfWeek = state.recipesOfWeek,
                    isRecipesOfWeekLoading = state.isRecipeOfWeekLoading,
                    recipesOfWeekError = state.recipeOfWeekError,
                    onRecipeClick = {recipe ->
                        navHostController.navigate(Destinations.RecipeDetailScreen(recipe.id))
                    },
                    onSeeAllRecipeOfWeek = {}, onRecipeFavouriteToggle = {recipe ->
                        viewModel.onAction(HomeAction.OnRecipeFavouriteToggle(recipe))
                    })
            }
        }
    }
}