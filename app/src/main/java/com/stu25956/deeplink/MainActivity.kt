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

/*
Filipe Lutz Mariano 25956
Assignment 8

Module: Mobile App Development 2
Lecturer: Eugene Oregan

To access the app from the DeepLink, use the following URL:
https://filipelutz.github.io/DeepLink

Note: This link must be opened in the web browser on the emulator
to trigger the DeepLink and navigate directly to the Music Player screen in the app.
Click the "Open Music Player" button on the web page to initiate the DeepLink navigation.
*/

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
                                uriPattern = "https://filipelutz.github.io/DeepLink/music"
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