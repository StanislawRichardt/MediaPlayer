package com.example.myapplication.ui.playbackscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun PlaybackScreen(playbackViewModel: PlaybackViewModel){
    MyApplicationTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            VideoPlayer(playbackViewModel)
        }
    }

}

@Composable
fun VideoPlayer(playbackViewModel: PlaybackViewModel){
    val player = playbackViewModel.player
    AndroidView(factory = { context -> PlayerView(context).apply { setPlayer(player) } })
}