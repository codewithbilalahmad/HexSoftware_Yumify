package com.muhammad.yumify.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.muhammad.yumify.utils.loadingEffect

@Composable
fun AppImage(modifier: Modifier = Modifier, url: String, shape: Shape = RoundedCornerShape(16.dp)) {
    val painter = rememberAsyncImagePainter(url)
    Box(modifier = modifier.clip(shape)) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        AnimatedVisibility(
            visible = painter.state is AsyncImagePainter.State.Loading,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Box(
                modifier = Modifier.matchParentSize()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .loadingEffect()
            )
        }
    }
}