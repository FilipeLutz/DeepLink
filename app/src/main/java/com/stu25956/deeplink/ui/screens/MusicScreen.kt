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
@Composable
fun MusicScreen(navController: NavController) {
    val songs = listOf(
        Triple("All I Want for Christmas Is You", "Mariah Carey", R.raw.christmas),
        Triple("Ghost Town", "Benson Boone", R.raw.ghost),
        Triple("Levitating", "Dua Lipa", R.raw.levitating),
        Triple("Save Your Tears", "The Weeknd", R.raw.save_your_tears),
        Triple("Stay", "The Kid LAROI", R.raw.stay)
    )

    val context = LocalContext.current
    val listState = rememberLazyListState()

    // State variables for playback
    var isPlaying by remember { mutableStateOf(false) }
    var currentSongIndex by remember { mutableIntStateOf(0) }
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var totalDuration by remember { mutableIntStateOf(0) }
    var mediaPlayer by remember {
        mutableStateOf(
            MediaPlayer.create(context, songs[currentSongIndex].third).apply {
                setOnPreparedListener {
                    totalDuration = duration
                }
            }
        )
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying && mediaPlayer?.isPlaying == true) {
            currentProgress = mediaPlayer!!.currentPosition.toFloat() / totalDuration
            delay(100)
        }
    }

    fun switchSong(index: Int) {
        if (index in songs.indices) {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, songs[index].third).apply {
                setOnPreparedListener {
                    totalDuration = duration
                    if (isPlaying) start()
                }
            }
            currentSongIndex = index
        }
    }

    fun playNextSong() {
        val nextIndex = if (currentSongIndex + 1 < songs.size) currentSongIndex + 1 else 0
        switchSong(nextIndex)
        mediaPlayer?.start()
        isPlaying = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (mediaPlayer?.isPlaying == true) mediaPlayer?.stop()
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            Modifier.size(30.dp)
                        )
                    }
                },
                title = { Text("Music Player") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Central Media Player UI Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = songs[currentSongIndex].first,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "by ${songs[currentSongIndex].second}",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        IconButton(
                            modifier = Modifier
                                .background(
                                    Color.DarkGray,
                                    shape = CircleShape),
                            onClick = {
                                if (currentSongIndex > 0) {
                                    switchSong(currentSongIndex - 1)
                                    if (isPlaying) mediaPlayer?.start()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SkipPrevious,
                                contentDescription = "Previous Song",
                                tint = Color.White
                            )
                        }

                        IconButton(
                            onClick = {
                                if (isPlaying) {
                                    mediaPlayer?.pause()
                                } else {
                                    mediaPlayer?.start()
                                }
                                isPlaying = !isPlaying
                            },
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color.Blue, shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = Color.White
                            )
                        }

                        IconButton(
                            modifier = Modifier
                                .background(
                                    Color.DarkGray,
                                    shape = CircleShape),
                            onClick = {
                                playNextSong()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SkipNext,
                                contentDescription = "Next Song",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Slider(
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Blue,
                            activeTrackColor = Color.Blue,
                            inactiveTrackColor = Color.Gray
                        ),
                        value = if (totalDuration > 0) currentProgress else 0f,
                        onValueChange = { newValue ->
                            if (totalDuration > 0) {
                                mediaPlayer?.seekTo((newValue * totalDuration).toInt())
                                currentProgress = newValue
                            }
                        },
                        valueRange = 0f..1f,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${formatTime((totalDuration * currentProgress).toInt())} / ${
                            formatTime(
                                totalDuration
                            )
                        }",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Songs List",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Text(
                text = "Click on a song to play",
                fontSize = 18.sp,
                color = Color.Gray
            )

           LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(songs) { index, song ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .clickable {
                                    switchSong(index); mediaPlayer?.start(); isPlaying = true
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = song.first,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
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

    DisposableEffect(Unit) {
        onDispose {
            if (mediaPlayer?.isPlaying == true) mediaPlayer?.stop()
            mediaPlayer?.release()
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(millis: Int): String {
    val seconds = millis / 1000
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}