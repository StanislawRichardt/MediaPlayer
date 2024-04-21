package com.example.myapplication.ui.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.eventscreen.EventListViewModel
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

//TODO: Usunąć to factory
class ScheduleListViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(ScheduleListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ScheduleListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
