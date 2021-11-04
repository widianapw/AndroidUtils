package id.widianapw.android_utils.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

fun String.toDate(format: String, timeZone: String? = null): Date? {
    val dateFormat = SimpleDateFormat(format)
    if (timeZone != null) {
        dateFormat.timeZone = TimeZone.getTimeZone(timeZone)
    }
    return dateFormat.parse(this)
}

fun String.toDateInMillis(fromFormat: String? = null, isConvertToLocal: Boolean? = null): Long {
    val isConvert = isConvertToLocal ?: true
    val format = fromFormat ?: "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    val dateFormat = SimpleDateFormat(format)
    if (isConvert) dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = dateFormat.parse(this)
    return date.time
}

fun String.toDateString(
    toFormat: String,
    timeZone: String? = null,
    fromFormat: String? = null,
    isConvertToLocal: Boolean? = null
): String {
    val isConvert = isConvertToLocal ?: true
    val from = fromFormat ?: "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    val dateFormat = SimpleDateFormat(from)
    if (isConvert) {
        if (timeZone != null) {
            dateFormat.timeZone = TimeZone.getTimeZone(timeZone)
        } else {
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    val date = dateFormat.parse(this)
    val toDateFormat = SimpleDateFormat(toFormat)
//    if (isConvert) toDateFormat.timeZone = TimeZone.getDefault()
    return toDateFormat.format(date)
}

fun Long.toDate(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    simpleDateFormat.timeZone = TimeZone.getDefault()
    return simpleDateFormat.format(Date(this))
}


fun Int.getString(context: Context,text: String? = null): String {
    return context.getString(this, text)
}

fun Int?.isNoll(): Boolean {
    return this == null || this == 0
}

fun Int?.isNotNoll(): Boolean {
    return this != null && this != 0
}

fun Int.getDrawable(context: Context): Drawable? {
    return ContextCompat.getDrawable(context, this)
}
