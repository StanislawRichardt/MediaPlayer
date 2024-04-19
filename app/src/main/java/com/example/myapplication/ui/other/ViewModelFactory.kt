package com.example.myapplication.ui.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.media3.exoplayer.ExoPlayer
import com.example.myapplication.ui.eventscreen.EventListViewModel
import com.example.myapplication.ui.playbackscreen.PlaybackViewModel
import com.example.myapplication.ui.schedulescreen.ScheduleListViewModel
import java.lang.IllegalArgumentException

class EventListViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(EventListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return EventListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ScheduleListViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(ScheduleListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ScheduleListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PlaybackViewModelFactory(private val player: ExoPlayer): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(PlaybackViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PlaybackViewModel(player) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}