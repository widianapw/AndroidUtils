package id.widianapw.androidutils.view.common

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.widianapw.androidutils.presenter.base.BaseDelegate
import kotlinx.coroutines.Job

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */
open class BaseActivity : AppCompatActivity(), BaseDelegate {
    val jobs = mutableListOf<Job>()
    override fun showError(title: String?, message: String?) {
        Toast.makeText(this, "$title - $message", Toast.LENGTH_SHORT).show()
    }

    fun disposeJobs() {
        jobs.forEach {
            it.cancel()
        }
    }
}