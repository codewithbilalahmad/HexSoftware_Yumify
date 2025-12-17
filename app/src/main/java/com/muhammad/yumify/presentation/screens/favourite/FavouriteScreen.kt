package com.muhammad.yumify.presentation.screens.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.muhammad.yumify.R
import com.muhammad.yumify.presentation.components.AppTextField
import com.muhammad.yumify.presentation.navigation.AppBottomBar
import com.muhammad.yumify.presentation.navigation.Destinations
import com.muhammad.yumify.presentation.screens.favourite.components.FavouriteSection
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navHostController: NavHostController,
    viewModel: FavouriteViewModel = koinViewModel(),
) {
    val layoutDirection = LocalLayoutDirection.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
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
    }, containerColor = MaterialTheme.colorScheme.surfaceContainer) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 24.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 24.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 32.dp
            ), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item("header") {
                Text(
                    text = stringResource(R.string.bookmark),
                    modifier = Modifier.fillMaxWidth().animateItem(),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            item("favourite_search_bar") {
                AppTextField(
                    value = state.searchQuery,
                    onValueChange = { newValue ->
                        viewModel.onAction(FavouriteAction.OnSearchQueryChange(newValue))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    hint = R.string.search_recipes,
                    leadingIcon = R.drawable.ic_search,
                    trailingIcon = R.drawable.ic_sort
                )
            }
            item("recently_viewed_recipes_section") {
                FavouriteSection(
                    recipes = state.recentViewedRecipes,
                    title = R.string.recently_viewed, onSeeAllRecipes = {
                        navHostController.navigate(Destinations.RecentlyViewedRecipesScreen)
                    }, emptyMessage = R.string.no_recently_viewed_recipes,
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 8.dp)
                        .animateItem(), emptyIcon = R.drawable.ic_viewed
                )
            }
            item("favourite_recipes_section") {
                FavouriteSection(
                    recipes = state.favouriteRecipes,
                    title = R.string.favourite, onSeeAllRecipes = {
                        navHostController.navigate(Destinations.FavouriteRecipesScreen)
                    }, emptyMessage = R.string.no_favourite_recipes,
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 8.dp)
                        .animateItem(),emptyIcon = R.drawable.ic_favourite_outlined
                )
            }
        }
    }
}
