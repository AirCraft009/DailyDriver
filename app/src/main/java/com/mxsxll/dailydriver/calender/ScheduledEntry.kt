package com.mxsxll.dailydriver.calender

import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.data.WeekDay
import kotlinx.datetime.LocalTime

class ScheduledEntry(createdWeek: Int, weekInd: Int, dayInd: Int, start: LocalTime, end: LocalTime, name: String, desc: String, col: Color) : CalenderEntry(dayInd,createdWeek, start, end, name, desc, col) {
    val week = weekInd
}