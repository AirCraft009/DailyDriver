package com.mxsxll.dailydriver.ui.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

private val Orange = Color(0xFFFF6D1F)
private val CellBorder = Color(0xFF2A2A2A)
private val TimeColumnWidth = 56.dp
private val HourRowHeight = 80.dp

data class CalendarDay(
    val label: String,   // "Mo", "Di", ...
    val dayOfMonth: Int,
    val isToday: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    monthLabel: String = "Sep.",
    days: List<CalendarDay> = defaultWeek(),
    startHour: Int = 8,
    endHour: Int = 18
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Month label, top left
            Text(
                text = monthLabel,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            // Day-of-week header row
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(TimeColumnWidth))
                days.forEach { day ->
                    DayHeaderCell(day)
                }
            }

            // Shared scroll state keeps the time column and the day grid in sync
            val scrollState = rememberScrollState()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // Time labels column
                Column(
                    modifier = Modifier
                        .width(TimeColumnWidth)
                        .verticalScroll(scrollState)
                ) {
                    for (hour in startHour until endHour) {
                        Box(
                            modifier = Modifier
                                .height(HourRowHeight)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                text = "%02d:00".format(hour),
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Day columns grid
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    days.forEach { day ->
                        Column(modifier = Modifier.weight(1f)) {
                            for (hour in startHour until endHour) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(HourRowHeight)
                                        .border(width = 0.5.dp, color = CellBorder)
                                        .background(
                                            if (day.isToday) Color(0xFF1A0E00) else Color.Black
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.DayHeaderCell(day: CalendarDay) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.label,
            color = if (day.isToday) Orange else Color.LightGray,
            fontSize = 14.sp
        )
        Text(
            text = day.dayOfMonth.toString(),
            color = if (day.isToday) Orange else Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Builds Mo–Sa for the current week, marking today
@RequiresApi(Build.VERSION_CODES.O)
private fun defaultWeek(): List<CalendarDay> {
    val labels = listOf("Mo", "Di", "Mi", "Do", "Fr", "Sa", "Su")
    val today = LocalDate.now()
    val monday = today.minusDays((today.dayOfWeek.value - 1).toLong())
    return labels.mapIndexed { index, label ->
        val date = monday.plusDays(index.toLong())
        CalendarDay(
            label = label,
            dayOfMonth = date.dayOfMonth,
            isToday = date == today
        )
    }
}