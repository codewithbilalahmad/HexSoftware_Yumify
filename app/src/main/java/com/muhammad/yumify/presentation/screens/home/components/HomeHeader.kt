package com.muhammad.yumify.presentation.screens.home.components

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.muhammad.yumify.R
import com.muhammad.yumify.domain.model.Recipe
import com.muhammad.yumify.presentation.components.AppImage
import com.muhammad.yumify.utils.loadingEffect
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    isPopularRecipeLoading: Boolean,
    popularRecipeError: String?,
    popularRecipes: List<Recipe>, onPopularRecipeClick: (Recipe) -> Unit,
    onSearchClick: () -> Unit, blurRadius: Dp = 16.dp,
) {
    val density = LocalDensity.current
    val pagerState =
        rememberPagerState(initialPage = popularRecipes.size / 2) { popularRecipes.size }
    var isUserInteracting by remember { mutableStateOf(false) }
    val blurRadiusPx = with(density) {
        blurRadius.toPx()
    }
    val currentRecipe by remember(popularRecipes, pagerState.currentPage) {
        derivedStateOf {
            if (popularRecipes.isNotEmpty())
                popularRecipes[pagerState.currentPage.coerceIn(0, popularRecipes.lastIndex)]
            else null
        }
    }
    val blurModifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Modifier.graphicsLayer {
            renderEffect =
                RenderEffect.createBlurEffect(blurRadiusPx, blurRadiusPx, Shader.TileMode.CLAMP)
                    .asComposeRenderEffect()
        }
    } else Modifier.blur(blurRadius)
    LaunchedEffect(pagerState) {
        pagerState.interactionSource.interactions.collect { interaction ->
            isUserInteracting = when (interaction) {
                is PressInteraction.Press, is DragInteraction.Start -> true
                else -> false
            }
        }
    }
    LaunchedEffect(isUserInteracting) {
        while (!isUserInteracting) {
            delay(4000L)
            val nextPage = (pagerState.currentPage + 1) % Int.MAX_VALUE
            pagerState.animateScrollToPage(
                nextPage,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    }
    Box(modifier = modifier) {
        when {
            isPopularRecipeLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .then(blurModifier)
                        .loadingEffect()
                )
            }

            currentRecipe != null -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)) {
                    AppImage(
                        url = currentRecipe?.imageUrl ?: return,
                        modifier = Modifier
                            .fillMaxSize()
                            .then(blurModifier)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(0.3f))
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = stringResource(R.string.app_desp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White
                            )
                        )
                    }
                }
                IconButton(
                    onClick = onSearchClick,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                        contentDescription = null
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = stringResource(R.string.popular),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_popular),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow.copy(alpha = 0.8f)
                )
            }
            if (popularRecipes.isNotEmpty()) {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 80.dp),
                ) { page ->
                    val recipe = popularRecipes[page]
                    PopularRecipeItem(
                        recipe = recipe,
                        index = page,
                        pagerState = pagerState,
                        onRecipeClick = onPopularRecipeClick
                    )
                }
            }
        }
    }
}