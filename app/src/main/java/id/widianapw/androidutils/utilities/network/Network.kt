package id.widianapw.androidutils.utilities.network

import android.util.Log
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import id.widianapw.androidutils.model.error.ErrorData
import id.widianapw.androidutils.presenter.base.BaseDelegate
import id.widianapw.androidutils.utilities.constant.Constants
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.net.UnknownHostException

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */


val client = HttpClient(Android) {
    install(Logging) {
        logger = object : io.ktor.client.features.logging.Logger {
            override fun log(message: String) {
                prettyLogger(message)
            }
        }
        level = LogLevel.ALL
    }
    engine {
        connectTimeout = 100_000
        socketTimeout = 100_000
    }

    install(JsonFeature) {
        serializer = GsonSerializer() {
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }

    defaultRequest {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        contentType(ContentType.Application.Json)
        host = Constants.baseUrl
        url {
            protocol = URLProtocol.HTTPS
        }
    }
}

private fun prettyLogger(message: String) {
    val logName = "LOGGER"
    if (message.startsWith("{") || message.startsWith("[")) {
        try {
            val prettyPrintJson =
                GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping()
                    .create()
                    .toJson(JsonParser().parse(message))
            if (prettyPrintJson.length > 4000) {
                val contentLength = prettyPrintJson.length
                val countDiv = contentLength / 4000
                for (i in 0 until countDiv step 1) {
                    val maxValue = (i + 1) * 4000
                    val startIndex = (4000 * i)
                    val maxValueToPrint = if (maxValue <= contentLength) maxValue else contentLength
                    Log.d(logName, prettyPrintJson.substring(startIndex, maxValueToPrint))
                }
            } else {
                Log.d(logName, prettyPrintJson)
            }
        } catch (m: JsonSyntaxException) {
            Log.d(logName, message)
        }
    } else {
        Log.d(logName, message)
        return
    }
}

suspend fun requestApi(baseDelegate: BaseDelegate, request: suspend () -> Unit) {
    try {
        request()
    } catch (e: Throwable) {
        errorHandler(e, baseDelegate)
    }
}

suspend fun errorHandler(exception: Throwable, baseDelegate: BaseDelegate) {
    when (exception) {
        is RedirectResponseException -> {
            val error = Klaxon().parse<ErrorData>(exception.response.readText())
            val showError = error?.error?.errors?.first()
            baseDelegate.showError(error?.error?.title, showError?.message)
        }
        is ClientRequestException -> {
            val error = Klaxon().parse<ErrorData>(exception.response.readText())
            val showError = error?.error?.errors?.first()
            baseDelegate.showError(error?.error?.title, showError?.message)
        }
        is ServerResponseException -> {
            val error = Klaxon().parse<ErrorData>(exception.response.readText())
            val showError = error?.error?.errors?.first()
            baseDelegate.showError(error?.error?.title, showError?.message)
        }
        is UnknownHostException -> {
            baseDelegate.showError("Koneksi internet", "Periksa kembali koneksi internet anda")
        }
    }
}
