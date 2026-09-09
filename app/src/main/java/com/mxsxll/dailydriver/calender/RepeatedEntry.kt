package com.mxsxll.dailydriver.calender

import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.data.WeekDay
import kotlinx.datetime.LocalTime

class RepeatedEntry(createdWeek: Int, dayInd: WeekDay, weekPer: Int, start: LocalTime, end: LocalTime, name: String, desc: String, col: Color) : CalenderEntry(createdWeek, start, end, name, desc, col)  {
    val day = dayInd
    val weekPeriod = weekPer
}