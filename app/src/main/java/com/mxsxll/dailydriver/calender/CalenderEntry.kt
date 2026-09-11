package com.mxsxll.dailydriver.calender

import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.data.durationBetween
import kotlinx.datetime.LocalTime
import kotlin.time.Duration

public abstract class CalenderEntry(day: Int, currentWeek: Int, start: LocalTime, end: LocalTime, name: String, desc: String, col: Color) {
    val startTime: LocalTime = start
    val endTime: LocalTime = end
    val title: String = name
    val description: String = desc
    val color: Color = col
    val duration: Duration = durationBetween(start, end)
    val createdWeek: Int = currentWeek
    val dayInd: Int = day
}