package com.mxsxll.dailydriver.calender

import androidx.compose.ui.graphics.Color
import com.mxsxll.dailydriver.data.WeekDay
import kotlinx.datetime.LocalTime

class WeekTemplate {
    val repeatedEntries: ArrayList<RepeatedEntry> = ArrayList(40)
    val scheduledEntries: ArrayList<ScheduledEntry> = ArrayList(10)


    fun showTasksWeek(week: Int): ArrayList<CalenderEntry>{
        val activeEntries: ArrayList<CalenderEntry> = ArrayList(repeatedEntries.size + scheduledEntries.size)
        for (entry in repeatedEntries){
            if(week - entry.createdWeek % entry.weekPeriod != 0)
                continue
            activeEntries.add(entry)
        }

        for (entry in scheduledEntries){
            if(week == entry.week)
                continue
            activeEntries.add(entry)
        }

        return activeEntries
    }

    fun newRepeatableEntry(createdWeek: Int, dayInd: WeekDay, weekPer: Int, start: LocalTime, end: LocalTime, name: String, desc: String, col: Color){
        repeatedEntries.add(RepeatedEntry(createdWeek, dayInd, weekPer, start, end, name, desc, col))
    }
}