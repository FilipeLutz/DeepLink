package com.stu25956.deeplink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.stu25956.deeplink.ui.screens.HomeScreen
import com.stu25956.deeplink.ui.screens.MusicScreen
import com.stu25956.deeplink.ui.theme.DeepLinkTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepLinkTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable(
                        route = "music",
                        deepLinks = listOf(
                            navDeepLink {
                                uriPattern = "https://filipelutz.github.io/DeepLink"
                                action = Intent.ACTION_VIEW
                            }
                        )
                    ) {
                        MusicScreen(navController)
                    }
                }
            }
        }
    }
}