package com.example.myapplication.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.eventscreen.EventListViewModel
import com.example.myapplication.ui.eventscreen.EventScreen
import com.example.myapplication.ui.playbackscreen.PlaybackScreen
import com.example.myapplication.ui.playbackscreen.PlaybackViewModel
import com.example.myapplication.ui.schedulescreen.ScheduleListViewModel
import com.example.myapplication.ui.schedulescreen.ScheduleScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun currentRoute(navController: NavController): String?{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioApp (eventListViewModel: EventListViewModel, scheduleListViewModel:ScheduleListViewModel, playbackViewModel: PlaybackViewModel) {
    val navController = rememberNavController()

    MyApplicationTheme {
        Scaffold (
         bottomBar = {NavigationBar(navController)},
        ){
            NavHost(navController = navController, startDestination = "events", Modifier.padding(it)) {
                composable("events"){
                    EventScreen(eventListViewModel, navController)
                }
                composable("playback"){
                    PlaybackScreen(playbackViewModel)
                }
                composable("schedule"){
                    ScheduleScreen(scheduleListViewModel,navController)
                }
            }
        }
    }

}

@Composable
fun NavigationBar(navController: NavController){
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ){
        val currentRoute = currentRoute(navController = navController)
        val items = listOf(
            BottomNavItem("Events", Icons.Filled.Star, "events"),
            BottomNavItem("Schedule", Icons.Filled.DateRange, "schedule")
        )

        items.forEach{item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route)},
                icon = {
                    Row {
                        Icon(item.icon, item.title)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(item.title)
                    }
                })
        }
    }
}

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)