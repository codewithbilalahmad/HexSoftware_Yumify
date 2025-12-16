package com.muhammad.yumify.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muhammad.yumify.utils.loadingEffect

@Composable
fun AppImage(modifier: Modifier = Modifier, url: String, shape: Shape = RoundedCornerShape(16.dp)) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    val imageRequest = remember(url) {
        ImageRequest.Builder(context).data(url).memoryCacheKey(url)
            .diskCacheKey(url).build()
    }
    Box(modifier = modifier.clip(shape)) {
        AsyncImage(
            model = imageRequest,
            onLoading = {
                isLoading = true
            }, onError = {
                isLoading = false
            }, onSuccess = {
                isLoading = false
            },
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
        AnimatedVisibility(
            visible = isLoading,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Box(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .loadingEffect()
            )
        }
    }
}