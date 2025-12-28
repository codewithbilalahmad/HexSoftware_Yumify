package com.muhammad.yumify.presentation.screens.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.R
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.presentation.components.AppImage
import com.muhammad.yumify.presentation.components.FavouriteIconButton

@Composable
fun NewRecipeCard(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onRecipeClick: (Recipe) -> Unit,
    onFavouriteToggle: (Recipe) -> Unit,
) {
    val density = LocalDensity.current
    var imageHeight by remember { mutableIntStateOf(0) }
    Card(modifier = modifier, shape = RoundedCornerShape(24.dp), onClick = {
        onRecipeClick(recipe)
    }, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(
                        width = 150.dp,
                        height = with(density) { imageHeight.toDp() })
                    .clip(RoundedCornerShape(24.dp)), contentAlignment = Alignment.Center
            ) {
                AppImage(
                    url = recipe.imageUrl,
                    modifier = Modifier.fillMaxSize()
                )
                FavouriteIconButton(
                    onFavouriteToggle = onFavouriteToggle,
                    recipe = recipe, isSmallIconButton = true,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(
                            Alignment.TopEnd
                        )
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .onSizeChanged { size ->
                        imageHeight = size.height
                    }
                    .padding(horizontal = 8.dp, vertical = 12.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = recipe.category,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surface)
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(com.muhammad.yumify.R.drawable.ic_rank),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "4.5",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.surface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_clock),
                            contentDescription = null, tint = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "${recipe.time} min",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.surface,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_calories),
                        contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "${recipe.calories} cal",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}