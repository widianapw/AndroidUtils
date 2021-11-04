package id.widianapw.android_utils.logger

import android.util.Log
import com.google.gson.GsonBuilder

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */
object WLog {
    fun obj(any: Any?){
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonPretty: String = gsonPretty.toJson(any)
        Log.d("LOGGER", jsonPretty)
    }
}