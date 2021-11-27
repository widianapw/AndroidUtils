package id.widianapw.android_utils.extensions

import kotlinx.coroutines.Job
import kotlinx.coroutines.job

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

fun Job.add(jobs: MutableList<Job>) {
    jobs.add(job)
}