package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.eventscreen.EventListViewModel
import com.example.myapplication.ui.navigation.PortfolioApp
import com.example.myapplication.ui.other.EventListViewModelFactory
import com.example.myapplication.ui.other.ScheduleListViewModelFactory
import com.example.myapplication.ui.schedulescreen.ScheduleListViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    private lateinit var eventListViewModel: EventListViewModel
    private lateinit var scheduleListViewModel: ScheduleListViewModel
    //TODO: Usunąć ScheduleListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListViewModel = ViewModelProvider(this, EventListViewModelFactory())[EventListViewModel::class.java]
        scheduleListViewModel = ViewModelProvider(this, ScheduleListViewModelFactory())[ScheduleListViewModel::class.java]
        //TODO: usunąć schedule list viewmodel
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PortfolioApp(eventListViewModel, scheduleListViewModel)
                }
            }
        }
    }
}
