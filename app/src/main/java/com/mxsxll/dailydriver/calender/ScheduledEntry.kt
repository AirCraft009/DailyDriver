package com.mxsxll.dailydriver.calender

import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.data.WeekDay
import kotlinx.datetime.LocalTime

class ScheduledEntry(weekInd: Int, dayInd: WeekDay, start: LocalTime, end: LocalTime, name: String, desc: String, col: Color) : CalenderEntry(start, end, name, desc, col) {
    val week = weekInd
    val day = dayInd
}