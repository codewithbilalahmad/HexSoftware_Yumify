package com.muhammad.yumify.presentation.screens.favourite_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.muhammad.yumify.R
import com.muhammad.yumify.presentation.navigation.Destinations
import com.muhammad.yumify.presentation.screens.favourite_recipes.components.FavouriteRecipeItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FavouriteRecipesScreen(
    navHostController: NavHostController,
    viewModel: FavouriteRecipesViewModel = koinViewModel(),
) {
    val layoutDirection = LocalLayoutDirection.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(R.string.favourite_recipes))
            },
            navigationIcon = {
                IconButton(onClick = {
                    navHostController.navigateUp()
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                        contentDescription = null
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        )
    }, containerColor = MaterialTheme.colorScheme.surfaceContainer) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                end = paddingValues.calculateRightPadding(layoutDirection) + 16.dp,
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 32.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.favouriteRecipes, key = { it.id }) { recipe ->
                FavouriteRecipeItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    onRecipeUnFavourite = { id ->
                        viewModel.onAction(FavouriteRecipeAction.OnUnFavouriteRecipe(id))
                    },
                    recipe = recipe, onRecipeClick = { recipe ->
                        navHostController.navigate(Destinations.RecipeDetailScreen(recipe.id))
                    }
                )
            }
        }
    }
}