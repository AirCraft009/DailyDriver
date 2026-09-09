package com.mxsxll.dailydriver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.ui.views.CalendarView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AppRoot() }
    }
}

sealed class Tab(val label: String) {
    object Home : Tab("Timetable")
    object Search : Tab("Habits")
    object Profile : Tab("Calender")
}

private val tabs = listOf(Tab.Home, Tab.Search, Tab.Profile)

@Composable
fun AppRoot() {
    MaterialTheme {
        var selectedTab by remember { mutableStateOf<Tab>(Tab.Home) }

        Scaffold(
            containerColor = Color.Black,
            bottomBar = {
                NavigationBar(containerColor = Color.Black) {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            icon = {},
                            label = { Text(tab.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                color = Color.Black
            ) {
                when (selectedTab) {
                    Tab.Home -> CalendarView()
                    Tab.Search -> BlankScreen()
                    Tab.Profile -> BlankScreen()
                }
            }
        }
    }
}


@Composable
fun BlankScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black))
}