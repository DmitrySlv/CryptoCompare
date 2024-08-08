package com.dscreate_app.cryptocompare.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null) return ""

    val stamp = Timestamp(timestamp * 1000)
    val date = Date(stamp.time)
    val timesPattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(timesPattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}