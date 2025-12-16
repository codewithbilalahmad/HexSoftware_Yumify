package com.muhammad.yumify.presentation.screens.category_recipes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.domain.model.CategoryRecipe
import com.muhammad.yumify.presentation.components.AppImage

@Composable
fun CategoryRecipeCard(
    modifier: Modifier = Modifier,
    recipe: CategoryRecipe,height : Dp,
    onCategoryRecipeClick: (CategoryRecipe) -> Unit,
) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(16.dp), border = BorderStroke(
            1.5.dp,
            MaterialTheme.colorScheme.surfaceVariant
        ), onClick = {
            onCategoryRecipeClick(recipe)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height), contentAlignment = Alignment.Center
        ) {
            AppImage(
                url = recipe.imageUrl,
                modifier = Modifier.matchParentSize(),
                shape = RoundedCornerShape(16.dp)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Color.Black.copy(0.4f)
                    )
            )
            Text(
                text = recipe.name,modifier = Modifier.padding(8.dp),
                maxLines = 2, overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White, textAlign = TextAlign.Center
                )
            )
        }
    }
}