package com.muhammad.yumify.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.muhammad.yumify.R
import com.muhammad.yumify.presentation.components.AppTextField
import com.muhammad.yumify.presentation.navigation.AppBottomBar
import com.muhammad.yumify.presentation.navigation.Destinations
import com.muhammad.yumify.presentation.screens.search.components.PopularChoicesSection
import com.muhammad.yumify.presentation.screens.search.components.SearchRecipeItem
import com.muhammad.yumify.presentation.screens.search.components.newRecipesSection
import com.muhammad.yumify.utils.loadingEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    viewModel: SearchViewModel = koinViewModel(),
) {
    val layoutDirection = LocalLayoutDirection.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val isFirstItem by remember(listState) {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset < 8
        }
    }
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
    }, topBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .statusBarsPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.search),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            AppTextField(
                value = state.searchQuery,
                onValueChange = { newValue ->
                    viewModel.onAction(SearchAction.OnSearchQueryChange(newValue))
                },
                modifier = Modifier.fillMaxWidth(),
                hint = R.string.search_recipes,
                leadingIcon = R.drawable.ic_search,
                trailingIcon = R.drawable.ic_sort
            )
        }
    }, containerColor = MaterialTheme.colorScheme.surfaceContainer) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection),
                end = paddingValues.calculateEndPadding(layoutDirection),
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 32.dp
            ), state = listState, verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when {
                state.isSearchLoading -> {
                    items(10, key = { it }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp).padding(horizontal = 16.dp)
                                .clip(
                                    RoundedCornerShape(16.dp)
                                )
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .loadingEffect()
                                .animateItem()
                        )
                    }
                }

                state.searchError != null -> {
                    item("error_section") {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                16.dp,
                                Alignment.CenterVertically
                            )
                        ) {
                            Image(
                                painter = painterResource(R.drawable.wireless),
                                contentDescription = null,
                                modifier = Modifier.size(80.dp)
                            )
                            Text(
                                text = state.searchError!!,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.error,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }

                state.searchRecipes.isNotEmpty() -> {
                    items(state.searchRecipes, key = { it.id }) { recipe ->
                        SearchRecipeItem(
                            recipe = recipe,
                            onRecipeClick = { recipe ->
                                navHostController.navigate(Destinations.RecipeDetailScreen(recipe.id))
                            },
                            onFavouriteRecipeToggle = { recipe ->

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .animateItem()
                        )
                    }
                }

                else -> {
                    item("PopularChoicesSection") {
                        PopularChoicesSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItem(),
                            popularChoices = state.popularChoices,
                            onRecipeClick = { recipe ->
                                navHostController.navigate(Destinations.RecipeDetailScreen(recipe.id))
                            },
                            onRecipeFavouriteToggle = {},
                            onSeeAllPopularRecipes = {},
                            popularChoicesError = state.popularChoiceError,
                            isPopularChoicesLoading = state.popularChoiceLoading
                        )
                    }
                    newRecipesSection(
                        newRecipes = state.newRecipes, onSeeAllNewRecipes = {},
                        isNewRecipesLoading = state.newRecipeLoading,
                        newRecipeError = state.newRecipeError
                    )
                }
            }
        }
    }
}