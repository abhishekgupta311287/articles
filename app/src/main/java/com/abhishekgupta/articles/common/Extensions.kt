package com.abhishekgupta.articles.common

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

fun Long.format(): String? {
    val number = this
    if (number < 1000) return "" + number
    val exp = (ln(number.toDouble()) / ln(1000.0)).toInt()
    val format = DecimalFormat("0.#")
    val value = format.format(number / 1000.0.pow(exp.toDouble()))
    return String.format("%s%c", value, "KMBTPE"[exp - 1])
}

fun String.formatStringDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.ENGLISH)
    val currentDate = Calendar.getInstance().time
    val serverDate = sdf.parse(this)
    val diff = abs(currentDate.time - serverDate.time)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    return when {
        hours > 24 -> {
            "${TimeUnit.MILLISECONDS.toDays(diff)} days"
        }
        hours in 1..23 -> {
            "${minutes / 60} hrs ${minutes % 60} Min"
        }
        else -> {
            "$minutes min"
        }
    }
}