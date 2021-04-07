package com.arny.metersreading.core.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun getDateTime(date: String?, pattern: String): DateTime? {
    return date?.let { dateString ->
        try {
            DateTimeFormat.forPattern(pattern).parseDateTime(dateString)
        } catch (e: Exception) {
            null
        }
    }
}

fun getDateTime(date: String?, inPattern: String, outPattern: String): String? {
    return date?.let { dateString ->
        try {
            DateTimeFormat.forPattern(inPattern).parseDateTime(dateString).toString(outPattern)
        } catch (e: Exception) {
            null
        }
    }
}
