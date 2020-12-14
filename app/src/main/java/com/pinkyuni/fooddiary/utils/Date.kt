package com.pinkyuni.fooddiary.utils

import java.text.SimpleDateFormat
import java.util.*

const val DATE_PATTERN = "dd.MM.yyyy"
const val TIME_PATTERN = "mm:ss"

fun Date.format(pattern: String = DATE_PATTERN): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun Date.formatTime(pattern: String = TIME_PATTERN): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}