package com.example.android.fitguuy.utils

import androidx.room.TypeConverter
import java.util.*
//TODO Ikke implementert grunnet tid
class TimeConverters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}