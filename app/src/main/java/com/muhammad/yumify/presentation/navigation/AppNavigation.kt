package com.muhammad.yumify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.muhammad.yumify.presentation.screens.categories.CategoriesScreen
import com.muhammad.yumify.presentation.screens.category_recipes.CategoryRecipesScreen
import com.muhammad.yumify.presentation.screens.home.HomeScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Destinations.HomeScreen) {
        composable<Destinations.HomeScreen>{
            HomeScreen(navHostController = navHostController)
        }
        composable<Destinations.SearchScreen>{  }
        composable<Destinations.FavouriteScreen>{  }
        composable<Destinations.RecipeDetailScreen>{
        }
        composable<Destinations.CategoriesScreen>{
            CategoriesScreen(navHostController = navHostController)
        }
        composable<Destinations.CategoryRecipesScreen>{
            val category = it.toRoute<Destinations.CategoryRecipesScreen>().category
            CategoryRecipesScreen(navHostController = navHostController, category = category)
        }
    }
}