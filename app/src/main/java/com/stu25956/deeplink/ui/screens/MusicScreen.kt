@file:Suppress("DEPRECATION")

package com.stu25956.deeplink.ui.screens

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.stu25956.deeplink.R
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
// Music Screen Composable
@Composable
fun MusicScreen(navController: NavController) {
    // List of songs to be played
    val songs = listOf(
        Triple("All I Want for Christmas Is You", "Mariah Carey", R.raw.christmas),
        Triple("Ghost Town", "Benson Boone", R.raw.ghost),
        Triple("Levitating", "Dua Lipa", R.raw.levitating),
        Triple("Save Your Tears", "The Weeknd", R.raw.save_your_tears),
        Triple("Stay", "The Kid Laroi, Justin Bieber", R.raw.stay)
    )
    // Get the current context
    val context = LocalContext.current
    // State variable to manage the list scroll position
    val listState = rememberLazyListState()

    // State variables for playback
    var isPlaying by remember { mutableStateOf(false) }
    var currentSongIndex by remember { mutableIntStateOf(0) }
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var totalDuration by remember { mutableIntStateOf(0) }
    // Media Player instance to play songs
    var mediaPlayer by remember {
        mutableStateOf(
            MediaPlayer.create(
                context, songs[currentSongIndex].third
            ).apply {
                setOnPreparedListener {
                    totalDuration = duration
                }
            }
        )
    }
    // Function to handle moving to the next track
    LaunchedEffect(isPlaying) {
        while (
            isPlaying && mediaPlayer?.isPlaying == true
        ) {
            currentProgress = mediaPlayer!!
                .currentPosition.toFloat() / totalDuration
            delay(100)
        }
    }

    // Function to switch to a new song
    fun switchSong(index: Int) {
        if (index in songs.indices) {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, songs[index].third).apply {
                setOnPreparedListener {
                    totalDuration = duration
                    start()
                }
            }
            currentSongIndex = index
            isPlaying = true
            currentProgress = 0f
        }
    }

    // Function to play the next song
    fun playNextSong() {
        val nextIndex = if (currentSongIndex + 1 < songs.size) currentSongIndex + 1 else 0
        switchSong(nextIndex)
        if (isPlaying) {
            mediaPlayer?.start()
        }
    }
    // Main UI for the Music Player
    Scaffold(
        // Top App Bar
        topBar = {
            TopAppBar(
                navigationIcon = {
                    // Back button to navigate back to the Home Screen
                    IconButton(
                        onClick = {
                            if (mediaPlayer?.isPlaying == true) mediaPlayer?.stop()
                            navController.popBackStack()
                        }
                    ) {
                        // Back Icon
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            Modifier.size(30.dp)
                        )
                    }
                },
                // Title of the Music Player Screen
                title = {
                    Text(
                        "Music Player",
                        fontSize = 24.sp,
                    )
                }
            )
        }
    ) { innerPadding ->
        // Main Column to hold the Media Player UI and the Music List
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            // Card to display the current song details
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                elevation = CardDefaults
                    .cardElevation(defaultElevation = 8.dp)
            ) {
                // Column to hold the song details
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Song Title
                    Text(
                        text = songs[currentSongIndex].first,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    // Song Artist
                    Text(
                        text = "by ${songs[currentSongIndex].second}",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    // Row to hold the playback controls
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Previous Song Button
                        IconButton(
                            modifier = Modifier
                                .background(
                                    Color.DarkGray,
                                    shape = CircleShape
                                ),
                            // Switch to the previous song
                            onClick = {
                                if (currentSongIndex > 0) {
                                    switchSong(currentSongIndex - 1)
                                    if (isPlaying) mediaPlayer?.start()
                                }
                            }
                        ) {
                            // Previous Icon
                            Icon(
                                imageVector = Icons.Filled.SkipPrevious,
                                contentDescription = "Previous Song",
                                tint = Color.White
                            )
                        }
                        // Play/Pause Button
                        IconButton(
                            onClick = {
                                // Play or Pause the current song
                                if (isPlaying) {
                                    mediaPlayer?.pause()
                                } else {
                                    mediaPlayer?.start()
                                }
                                isPlaying = !isPlaying
                            },
                            modifier = Modifier
                                .size(60.dp)
                                .background(
                                    Color.Blue,
                                    shape = CircleShape
                                )
                        ) {
                            // Play/Pause Icon
                            Icon(
                                imageVector =
                                if (isPlaying) Icons.Filled.Pause
                                else Icons.Filled.PlayArrow,
                                contentDescription =
                                if (isPlaying) "Pause"
                                else "Play",
                                tint = Color.White
                            )
                        }
                        // Next Song Button
                        IconButton(
                            modifier = Modifier
                                .background(
                                    Color.DarkGray,
                                    shape = CircleShape
                                ),
                            // Switch to the next song
                            onClick = {
                                playNextSong()
                            }
                        ) {
                            // Next Icon
                            Icon(
                                imageVector = Icons.Filled.SkipNext,
                                contentDescription = "Next Song",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Handle automatic reset and playback logic on song completion
                    mediaPlayer?.setOnCompletionListener {
                        // Play the next song when the current song ends
                        val nextIndex =
                            // Play the first song if the current song is the last one
                            if (currentSongIndex + 1 < songs.size)
                                currentSongIndex + 1 else 0
                        // Switch to the next song
                        switchSong(nextIndex)
                    }

                    // Handle playback slider progress and media player synchronization
                    LaunchedEffect(isPlaying, totalDuration) {
                        // Poll the media player for playback progress
                        while (true) {
                            // Update the playback progress if the song is playing
                            if (mediaPlayer != null
                                && isPlaying
                                && mediaPlayer!!.isPlaying
                            ) {
                                // Update the playback progress
                                currentProgress =
                                    mediaPlayer!!.currentPosition
                                        .toFloat() / totalDuration
                            }
                            // Delay to avoid excessive polling
                            delay(100)
                        }
                    }

                    // Slider to control progress and show accurate playback progress
                    Slider(
                        // Customize the slider colors
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Blue,
                            activeTrackColor = Color.Blue,
                            inactiveTrackColor = Color.Gray
                        ),
                        // Set the slider value to the current progress
                        value = currentProgress,
                        // Update the media player position on value change
                        onValueChange = { newValue ->
                            // Update the media player position
                            if (totalDuration > 0) {
                                // Calculate the new position based on the slider value
                                val newPosition = (newValue * totalDuration).toInt()
                                mediaPlayer?.seekTo(newPosition)
                                currentProgress = newValue
                            }
                        },
                        // Set the value range from 0 to 1
                        valueRange = 0f..1f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    // Text to display the current playback progress
                    Text(
                        // Format the playback progress and total duration
                        text =
                        "${formatTime((currentProgress * totalDuration).toInt())} / ${
                            formatTime(totalDuration)
                        }",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            // Text to display the Music List title
            Text(
                text = "Music List",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            // Text to display the Music List description
            Text(
                text = "Click on a song to play",
                fontSize = 18.sp,
                color = Color.Gray
            )
            // Lazy Column to display the list of songs
            LazyColumn(
                // Remember the scroll state to maintain position
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Iterate over the list of songs
                itemsIndexed(songs) { index, song ->
                    // Card to display the song details
                    Card(
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 14.dp,
                                bottom =
                                if (index == songs.lastIndex) 14.dp
                                else 0.dp
                            )
                            .fillMaxWidth()
                            .height(70.dp)
                            // Play the selected song on click
                            .clickable {
                                switchSong(index)
                                mediaPlayer?.start()
                                isPlaying = true
                            },
                        // Customize the card elevation
                        elevation = CardDefaults
                            .cardElevation(defaultElevation = 4.dp)
                    ) {
                        // Row to hold the song details
                        Row(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Column to hold the song index
                            Column {
                                // Song Title
                                Text(
                                    text = song.first,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                                // Song Artist
                                Text(
                                    text = "by ${song.second}",
                                    fontSize = 18.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    // Dispose the media player when the screen is removed
    DisposableEffect(Unit) {
        onDispose {
            // Release the media player resources
            if (mediaPlayer?.isPlaying == true)
                mediaPlayer?.stop()
                mediaPlayer?.release()
        }
    }
}
// Function to format the time in minutes and seconds
@SuppressLint("DefaultLocale")
fun formatTime(millis: Int): String {
    val seconds = millis / 1000
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}