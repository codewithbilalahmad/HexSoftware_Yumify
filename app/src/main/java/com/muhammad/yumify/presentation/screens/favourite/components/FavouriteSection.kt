package com.muhammad.yumify.presentation.screens.favourite.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.R
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.presentation.components.AppImage
import com.muhammad.yumify.utils.rippleClickable

@Composable
fun FavouriteSection(
    modifier: Modifier = Modifier,
    @StringRes emptyMessage: Int,emptyIcon : Int,
    recipes: List<Recipe>, @StringRes title: Int,
    onSeeAllRecipes: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(R.string.see_all),
                modifier = Modifier.rippleClickable(onClick = onSeeAllRecipes),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            )
        }
        when {
            recipes.isEmpty() -> {
                Icon(
                    imageVector = ImageVector.vectorResource(emptyIcon),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = stringResource(emptyMessage),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.Center
                    )
                )
            }

            recipes.size == 1 -> {
                AppImage(
                    url = recipes.first().imageUrl, shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(208.dp)
                        .border(
                            width = 1.5.dp,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(16.dp)
                        )
                )
            }

            recipes.size == 2 -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(208.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppImage(
                        url = recipes.first().imageUrl, shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .border(
                                width = 1.5.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                    AppImage(
                        url = recipes.drop(1).first().imageUrl, shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .border(
                                width = 1.5.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                }
            }

            recipes.size == 3 -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(208.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppImage(
                        url = recipes.first().imageUrl, shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1.5f)
                            .height(208.dp)
                            .border(
                                width = 1.5.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
                        AppImage(
                            url = recipes.drop(1).first().imageUrl,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        )
                        AppImage(
                            url = recipes.drop(2).first().imageUrl,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        )
                    }
                }
            }

            else -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(208.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppImage(
                        url = recipes.first().imageUrl, shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight()
                            .border(
                                width = 1.5.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        AppImage(
                            url = recipes.drop(1).first().imageUrl,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp), contentAlignment = Alignment.Center
                        ) {
                            AppImage(
                                url = recipes.drop(2).first().imageUrl,
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .border(
                                        width = 1.5.dp,
                                        color = MaterialTheme.colorScheme.surfaceVariant,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(
                                        Color.Black.copy(0.4f)
                                    )
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.matchParentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${recipes.size - 3}+",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = stringResource(R.string.recipes),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}