package com.mxsxll.dailydriver.data

import androidx.compose.ui.graphics.Color
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import kotlinx.datetime.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Entity
data class CalenderEntry (
    @PrimaryKey val rawEntryId: Int,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val title: String,
    val description: String,
    val color: Color
) {
    val duration: Duration
        get() = durationBetween(startTime, endTime)
}

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CalenderEntry::class,
            parentColumns = ["rawEntryId"],
            childColumns = ["rawEntryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["rawEntryId"])]
)
data class ScheduledEntry @OptIn(ExperimentalTime::class) constructor(
    val rawEntryId: Int,
    val week: Int
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CalenderEntry::class,
            parentColumns = ["rawEntryId"],
            childColumns = ["rawEntryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["rawEntryId"])]
)
data class RepeatedEntry(
    val rawEntryId: Int,
    val day: Int,
    val weekPeriod: Int,
)

fun durationBetween(start: LocalTime, end: LocalTime): Duration {
    val startMinutes = start.hour * 60 + start.minute
    val endMinutes = end.hour * 60 + end.minute

    var diff = endMinutes - startMinutes
    if (diff < 0) diff += 24 * 60  // crosses midnight

    return diff.minutes
}
