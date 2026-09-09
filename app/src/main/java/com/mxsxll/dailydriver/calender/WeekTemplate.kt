package com.mxsxll.dailydriver.calender

class WeekTemplate {
    val repeatedEntries: ArrayList<RepeatedEntry> = ArrayList(40)
    val scheduledEntries: ArrayList<ScheduledEntry> = ArrayList(10)


    fun showTasksWeek(week: Int): ArrayList<CalenderEntry>{
        val activeEntries: ArrayList<CalenderEntry> = ArrayList(repeatedEntries.size + scheduledEntries.size)
        for (entry in repeatedEntries){
            if(week % entry.weekPeriod != 0)
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
}