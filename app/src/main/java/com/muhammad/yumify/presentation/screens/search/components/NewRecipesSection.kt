package com.muhammad.yumify.presentation.screens.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

fun LazyListScope.newRecipesSection(
    isNewRecipesLoading: Boolean,
    newRecipeError: String?,onSeeAllNewRecipes  :() -> Unit,
    newRecipes: List<Recipe>,
) {
    when {
        isNewRecipesLoading -> {
            items(10, key = { it }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(
                            MaterialTheme.colorScheme.background
                        )
                        .animateItem()
                        .loadingEffect()
                )
            }
        }

        newRecipeError != null -> {
            item("new_recipe_error_section") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.wireless),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = newRecipeError,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }

        else -> {
            item("new_recipes_title"){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.new_recipes),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = stringResource(R.string.see_all),
                        modifier = Modifier.rippleClickable(
                            onClick = onSeeAllNewRecipes
                        ), style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.surface,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            items(newRecipes, key = { it.id }, contentType = {
                "new_recipe${it.id}"
            }) { recipe ->
                NewRecipeCard(
                    recipe = recipe,
                    onRecipeClick = {},
                    onFavouriteToggle = {},
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).animateItem()
                )
            }
        }
    }
}