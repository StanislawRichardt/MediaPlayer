package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.eventscreen.EventListViewModel
import com.example.myapplication.ui.navigation.PortfolioApp
import com.example.myapplication.ui.other.EventListViewModelFactory
import com.example.myapplication.ui.other.ScheduleListViewModelFactory
import com.example.myapplication.ui.schedulescreen.ScheduleListViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    private val eventListViewModel: EventListViewModel by viewModels()
    private val scheduleListViewModel: ScheduleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PortfolioApp(eventListViewModel, scheduleListViewModel)
                }
            }
        }
    }
}
