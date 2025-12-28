package com.muhammad.yumify.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.R
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.utils.loadingEffect
import com.muhammad.yumify.utils.rippleClickable

@Composable
fun RecommendedRecipesSection(
    modifier: Modifier = Modifier,
    recommendedRecipes: List<Recipe>,
    onRecipeClick : (Recipe) -> Unit,
    isRecommendedRecipesLoading: Boolean,onRecipeFavouriteToggle : (Recipe) -> Unit,
    recommendedRecipeError: String?,
    onSeeAllRecommendedRecipes: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.recommended),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(R.string.see_all),
                modifier = Modifier.rippleClickable(
                    onClick = onSeeAllRecommendedRecipes
                ), style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        when {
            isRecommendedRecipesLoading -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    items(10, key = {it}) {
                        Box(
                            modifier = Modifier
                                .size(width = 150.dp, height = 200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .loadingEffect()
                        )
                    }
                }
            }

            recommendedRecipeError != null -> {
                Image(
                    painter = painterResource(R.drawable.wireless),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = recommendedRecipeError,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                )
            }

            else -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    items(recommendedRecipes, key = { it.id }, contentType = {
                        "recommended_recipes${it.id}"
                    }) { recipe ->
                        RecommendedRecipeItem(recipe = recipe, onRecipeClick = onRecipeClick, onRecipeFavouriteToggle = onRecipeFavouriteToggle)
                    }
                }
            }
        }
    }
}