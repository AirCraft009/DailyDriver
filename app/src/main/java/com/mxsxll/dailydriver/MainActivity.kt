package com.mxsxll.dailydriver

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import com.mxsxll.dailydriver.calender.WeekTemplate
import com.mxsxll.dailydriver.ui.views.CalendarView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.util.Locale

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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
                    Tab.Home -> {
                        // Source - https://stackoverflow.com/a/61414652
                        // Posted by J7bits
                        // Retrieved 2026-09-09, License - CC BY-SA 4.0

                        val c = Calendar.getInstance()

                        val week = c.get(Calendar.WEEK_OF_YEAR)
                        val month = SimpleDateFormat("MMM", Locale.getDefault()).format(c.time)
                        val timetable = WeekTemplate()

                        CalendarView(monthLabel = month, timetable = timetable, week = week)
                    }
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