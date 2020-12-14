package com.pinkyuni.fooddiary.utils

import java.text.SimpleDateFormat
import java.util.*

const val DATE_PATTERN = "dd.MM.yyyy"
const val TIME_PATTERN = "HH:mm"

fun Date.format(pattern: String = DATE_PATTERN): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun Date.formatTime(pattern: String = TIME_PATTERN): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun getDateMillis(): Long {
    val cal = Calendar.getInstance()
    cal[Calendar.HOUR_OF_DAY] = 0
    cal[Calendar.MINUTE] = 0
    cal[Calendar.SECOND] = 0
    cal[Calendar.MILLISECOND] = 0
    return cal.timeInMillis
}