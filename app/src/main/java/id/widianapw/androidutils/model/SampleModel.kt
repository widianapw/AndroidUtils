package id.widianapw.androidutils.model

import kotlinx.serialization.*

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

@Serializable
data class SampleModel(
    val name: String,
    val age: Int,
    val count: Int
)