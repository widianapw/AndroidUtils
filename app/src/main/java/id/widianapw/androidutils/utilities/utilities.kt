package id.widianapw.androidutils.utilities

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

 fun doRequest(function: suspend () -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main) {
        function()
    }
}