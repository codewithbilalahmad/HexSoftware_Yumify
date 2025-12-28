package com.muhammad.yumify.presentation.screens.favourite_recipes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.R
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.presentation.components.AppImage
import com.muhammad.yumify.presentation.components.FavouriteIconButton
import com.muhammad.yumify.utils.rippleClickable

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FavouriteRecipeItem(
    modifier: Modifier = Modifier,
    onRecipeUnFavourite: (String) -> Unit,
    recipe: Recipe,
    onRecipeClick: (Recipe) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(top = 60.dp)
            .rippleClickable(onClick = {
                onRecipeClick(recipe)
            })
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(120.dp).offset(y = -(60).dp),
                contentAlignment = Alignment.Center
            ) {
                AppImage(
                    url = recipe.imageUrl, modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_rank),
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
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = recipe.name,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_calories),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp),
                                    tint = MaterialTheme.colorScheme.surface
                                )
                                Text(
                                    text = stringResource(R.string.calories),
                                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surface)
                                )
                            }
                            Text(
                                text = "${recipe.calories} ${stringResource(R.string.cal)}",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.surface)
                            )
                        }
                    }
                    FavouriteIconButton(
                        onFavouriteToggle = {
                            onRecipeUnFavourite(recipe.id)
                        },
                        isSmallIconButton = true,
                        recipe = recipe
                    )
                }
            }
        }
    }
}