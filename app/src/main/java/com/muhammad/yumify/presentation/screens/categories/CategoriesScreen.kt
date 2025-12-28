package com.muhammad.yumify.presentation.screens.categories

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.muhammad.yumify.R
import com.muhammad.yumify.presentation.components.AppTextField
import com.muhammad.yumify.presentation.navigation.Destinations
import com.muhammad.yumify.presentation.screens.categories.components.CategoryCard
import com.muhammad.yumify.utils.loadingEffect
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    viewModel: CategoriesViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val layoutDirection = LocalLayoutDirection.current
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
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
            title = {
                Text(text = stringResource(R.string.categories))
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        )
    }, containerColor = MaterialTheme.colorScheme.surfaceContainer) { paddingValues ->
        when {
            state.isCategoriesLoading -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 16.dp, contentPadding = PaddingValues(
                        start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                        end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                        top = paddingValues.calculateTopPadding() + 8.dp,
                        bottom = paddingValues.calculateBottomPadding() + 32.dp
                    ),
                    modifier = Modifier.fillMaxSize(), userScrollEnabled = false
                ) {
                    items(10, key = { it }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(
                                    RoundedCornerShape(16.dp)
                                )
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .loadingEffect()
                        )
                    }
                }
            }

            state.categoriesError != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                            end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                            top = paddingValues.calculateTopPadding() + 16.dp,
                            bottom = paddingValues.calculateBottomPadding() + 16.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(R.drawable.wireless),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = state.categoriesError!!,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            else -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 16.dp, contentPadding = PaddingValues(
                        start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                        end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                        top = paddingValues.calculateTopPadding() + 8.dp,
                        bottom = paddingValues.calculateBottomPadding() + 32.dp
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = StaggeredGridItemSpan.FullLine, key = "category_searchBar", contentType = {
                        "category_searchBar"
                    }) {
                        AppTextField(
                            value = state.query, hint = R.string.search_categories,
                            onValueChange = { newValue ->
                                viewModel.onAction(CategoriesAction.OnSearchQueryChange(newValue))
                            },
                            leadingIcon = R.drawable.ic_search, trailingIcon = R.drawable.ic_sort,
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItem()
                        )
                    }
                    itemsIndexed(state.categories, key = { id, _ -> id }, contentType = {id, _ ->
                        "category_$id"
                    }) { index, category ->
                        val height = if (index % 2 == 0) 250.dp else 200.dp
                        CategoryCard(
                            category = category, onCategoryClick = {category ->
                                navHostController.navigate(Destinations.CategoryRecipesScreen(category.name))
                            }, height = height,
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItem()
                        )
                    }
                }
            }
        }
    }
}