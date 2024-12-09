package com.stu25956.deeplink.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold{
        // Top App Bar
        TopAppBar(
            title = {
                // Title of the Top App Bar
                Text(
                    text = "Home Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        )
    }
    // Column to align the button in the center of the screen
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ){
        // Button to navigate to the Music Screen
        Button(
            modifier = Modifier
                .height(55.dp)
                .width(250.dp),
            // Navigate to the Music Screen
            onClick = {
                navController.navigate("music")
            }
        ) {
            // Text of the Button
            Text(
                text = "Go to Music Player",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}