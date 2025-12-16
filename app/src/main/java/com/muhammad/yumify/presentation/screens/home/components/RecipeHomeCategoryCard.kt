package com.muhammad.yumify.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.domain.model.RecipeCategory
import com.muhammad.yumify.presentation.components.AppImage
import com.muhammad.yumify.utils.rippleClickable

@Composable
fun RecipeHomeCategoryCard(
    modifier: Modifier = Modifier,
    category: RecipeCategory,
    onCategoryClick: (RecipeCategory) -> Unit,
) {
    Column(
        modifier = modifier
            .width(70.dp)
            .rippleClickable(onClick = {
                onCategoryClick(category)
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(category.color, CircleShape), contentAlignment = Alignment.Center
        ) {
            AppImage(modifier = Modifier.size(40.dp), shape = CircleShape, url = category.imageUrl)
        }
        Text(
            text = category.name,
            maxLines = 1, overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Bold
            )
        )
    }
}