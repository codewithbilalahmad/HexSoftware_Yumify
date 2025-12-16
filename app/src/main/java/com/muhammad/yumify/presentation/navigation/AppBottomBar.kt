package com.muhammad.yumify.presentation.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppBottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    Card(
        shape = CircleShape, modifier = modifier,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BottomNavItems.entries.forEach { screen ->
                val isSelectedScreen = currentDestination?.hierarchy?.any {
                    it.route == screen.route::class.qualifiedName
                } == true
                val icon by animateIntAsState(
                    targetValue = if (isSelectedScreen) screen.selectedIcon else screen.unSelectedIcon,
                    animationSpec = MaterialTheme.motionScheme.fastEffectsSpec(),
                    label = "icon"
                )
                val containerColor by animateColorAsState(
                    targetValue = if (isSelectedScreen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                    animationSpec = MaterialTheme.motionScheme.fastEffectsSpec(),
                    label = "contentColor"
                )
                val contentColor by animateColorAsState(
                    targetValue = if (isSelectedScreen) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.surface,
                    animationSpec = MaterialTheme.motionScheme.fastEffectsSpec(),
                    label = "containerColor"
                )
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(containerColor)
                        .clickable{
                            if(isSelectedScreen) return@clickable
                            navController.navigate(screen.route){
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(icon),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = contentColor
                    )
                }
            }
        }
    }
}