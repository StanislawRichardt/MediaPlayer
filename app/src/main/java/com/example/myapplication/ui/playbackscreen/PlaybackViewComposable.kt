package com.example.myapplication.ui.playbackscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaybackScreen(url:String, navController: NavController){
    MyApplicationTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.Close,
                            contentDescription = "Close video")
                    }
                }
            )
            VideoView(
                Modifier
                    .padding(10.dp)
                    .align(Alignment.Center)
                , url)
        }
    }
}

@Composable
fun VideoView( modifier: Modifier = Modifier, url: String){

    val context = LocalContext.current
    val playerView = remember { PlayerView(context) }
    val player = remember { ExoPlayer.Builder(context).build() }

    AndroidView({ playerView }, modifier){ view ->
        player.setMediaItem(MediaItem.fromUri(url))
        view.player = player
        player.prepare()
        player.playWhenReady = true
    }
    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }
}