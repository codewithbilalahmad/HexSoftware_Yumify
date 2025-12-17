package com.muhammad.yumify.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.muhammad.yumify.R
import com.muhammad.yumify.presentation.components.AppTextField
import com.muhammad.yumify.presentation.navigation.AppBottomBar
import com.muhammad.yumify.presentation.navigation.Destinations
import com.muhammad.yumify.presentation.screens.search.components.SearchRecipeItem
import com.muhammad.yumify.utils.loadingEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    viewModel: SearchViewModel = koinViewModel(),
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
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 32.dp
            ), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item("header") {
                Text(
                    text = stringResource(R.string.search),
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            item("favourite_search_bar") {
                AppTextField(
                    value = state.searchQuery,
                    onValueChange = { newValue ->
                        viewModel.onAction(SearchAction.OnSearchQueryChange(newValue))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    hint = R.string.search_recipes,
                    leadingIcon = R.drawable.ic_search,
                    trailingIcon = R.drawable.ic_sort
                )
            }
            when {
                state.isSearchLoading -> {
                    items(10, key = { it }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
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
                    items(state.searchRecipes, key = {it.id}){recipe ->
                        SearchRecipeItem(recipe = recipe, onRecipeClick = {recipe ->
                            navHostController.navigate(Destinations.RecipeDetailScreen(recipe.id))
                        }, onFavouriteRecipeToggle = {recipe ->

                        })
                    }
                }

                else -> {
                    item("search_intro") {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(
                                16.dp,
                                Alignment.CenterVertically
                            )
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
                                contentDescription = null,
                                modifier = Modifier.size(80.dp)
                            )
                            Text(
                                text = stringResource(R.string.search_recipes),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.error,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}