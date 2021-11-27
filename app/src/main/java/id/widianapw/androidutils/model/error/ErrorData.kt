package id.widianapw.androidutils.model.error

import io.ktor.http.ContentType.Application.Json
import io.ktor.utils.io.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */
@Serializable
data class ErrorData(val error: ErrorItem)
data class ErrorItem(val code: Int, val title: String, val errors: List<ErrorList>)
data class ErrorList(val title: String, val message: String)
