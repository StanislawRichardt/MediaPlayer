package com.example.myapplication.ui.playbackscreen

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer

class PlaybackViewModel(val player:ExoPlayer): ViewModel() {

    fun stopPlayer(){
        player.release()
    }

}