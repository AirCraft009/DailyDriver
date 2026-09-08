package com.mxsxll.dailydriver.calender

import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.data.durationBetween
import kotlinx.datetime.LocalTime
import kotlin.time.Duration

public abstract class CalenderEntry(start: LocalTime, end: LocalTime, name: String, desc: String, col: Color) {
    var startTime: LocalTime = start
    var endTime: LocalTime = end
    var title: String = name
    var description: String = desc
    var color: Color = col
    var duration: Duration = durationBetween(start, end)
}