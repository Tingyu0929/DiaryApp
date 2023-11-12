package com.example.diaryapp.utils

import com.example.diaryapp.data.model.Diary
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun Diary.toDateString(): String {
    if (date == null) return "NAN"
    val timeZone = TimeZone.currentSystemDefault()
    val result = Instant.fromEpochMilliseconds(this.date).toLocalDateTime(timeZone)
    val date = result.date
    val time = result.time.toString().substring(0, 5)
    return "$date $time"
}