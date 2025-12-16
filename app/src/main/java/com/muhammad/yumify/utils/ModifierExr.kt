package com.muhammad.yumify.utils

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.rippleClickable(onClick: () -> Unit, enabled: Boolean = true): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.clickable(
        enabled = enabled,
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )
}

@Composable
fun Modifier.loadingEffect(
    colors: List<Color> = listOf(
        MaterialTheme.colorScheme.surface.copy(0.6f),
        MaterialTheme.colorScheme.surface.copy(0.2f),
        MaterialTheme.colorScheme.surface.copy(0.6f),
    ),
): Modifier {
    val transition = rememberInfiniteTransition(label = "loadingEffect")
    val translate by transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "translateAnim"
    )
    return this.drawBehind {
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(x = translate - 1000f, y = 0f),
                end = Offset(x = translate, y = size.height)
            )
        )
    }
}