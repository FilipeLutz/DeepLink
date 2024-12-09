package com.stu25956.deeplink.utils

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer

fun switchSong(
    context: Context,
    songs: List<Triple<String, String, Int>>,
    mediaPlayer: MediaPlayer?,
    index: Int,
    isPlaying: Boolean,
    onMediaPlayerChanged: (MediaPlayer?) -> Unit,
    onCurrentSongIndexChanged: (Int) -> Unit,
    onTotalDurationChanged: (Int) -> Unit,
    playNextSong: () -> Unit
) {
    if (index in songs.indices) {
        mediaPlayer?.release()
        val newMediaPlayer = MediaPlayer.create(context, songs[index].third).apply {
            setOnPreparedListener {
                onTotalDurationChanged(duration)
                if (isPlaying) start()
            }
            setOnCompletionListener {
                playNextSong()
            }
        }
        onMediaPlayerChanged(newMediaPlayer)
        onCurrentSongIndexChanged(index)
    }
}

fun playNextSong(
    context: Context,
    songs: List<Triple<String, String, Int>>,
    currentSongIndex: Int,
    switchSong: (Int) -> Unit
) {
    val nextIndex = if (currentSongIndex + 1 < songs.size) currentSongIndex + 1 else 0
    switchSong(nextIndex)
}

@SuppressLint("DefaultLocale")
fun formatTime(millis: Int): String {
    val seconds = millis / 1000
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}