package id.widianapw.android_utils.common

import java.util.regex.Pattern

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */
object Const {
    const val READ_DATE_FORMAT = "EEEE, dd MMMM yyyy"
    const val READ_DATETIME_FORMAT = "EEEE, dd MMMM yyyy, kk:mm"
    const val PRINT_DATETIME_FORMAT = "E,dd-MM-yyyy, kk:mm"
    const val READ_TIME_FORMAT = "kk:mm a"

    const val DATE_API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"

    object Validation{
        val EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9\\._\\-\\+]+@[a-zA-Z0-9_\\-]+\\.[a-zA-Z\\.]+[a-zA-Z]$")
        val NUMBER_PATTERN = Pattern.compile("^[0-9]+$")
        val ALPHANUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9_]*\$")
    }
}