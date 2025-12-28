package com.muhammad.yumify.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.R
import com.muhammad.yumify.domain.model.Recipe

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FavouriteIconButton(
    modifier: Modifier = Modifier, onFavouriteToggle: (Recipe) -> Unit,
    recipe: Recipe,
    isSmallIconButton: Boolean,
) {
    val hapticFeedback = LocalHapticFeedback.current
    IconButton(
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            onFavouriteToggle(recipe)
        },
        modifier = modifier.then(if (isSmallIconButton) Modifier.size(IconButtonDefaults.extraSmallContainerSize()) else Modifier),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
    ) {
        val iconSize = if(isSmallIconButton) IconButtonDefaults.extraSmallIconSize else IconButtonDefaults.mediumIconSize
        val animatedSize by animateDpAsState(
            targetValue = if(recipe.isFavourite) (iconSize.value * 1.2f).dp else iconSize, animationSpec = keyframes {
                durationMillis = 450
                iconSize * 0.8f at 120
                iconSize * 1.3f at 260
                iconSize * 1.2f
            }
        )
        val icon = if (recipe.isFavourite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite_outlined
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier.size(animatedSize)
        )
    }
}