package com.muhammad.yumify

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.muhammad.yumify.presentation.navigation.AppNavigation
import com.muhammad.yumify.presentation.theme.YumifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT,Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT,Color.TRANSPARENT)
        )
        setContent {
            YumifyTheme {
                val navHostController = rememberNavController()
                AppNavigation(navHostController = navHostController)
            }
        }
    }
}