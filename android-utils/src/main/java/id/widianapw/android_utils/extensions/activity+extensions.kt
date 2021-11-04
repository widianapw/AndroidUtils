package id.widianapw.android_utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */


fun Activity.hideKeyboard() {
    currentFocus?.clearFocus()
    hideKeyboard(currentFocus ?: View(this))
}


fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}


fun Context.showKeyboard(
    view: View?
) {
    val imm =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInputFromWindow(view?.windowToken, 0, 0)
    view?.clearFocus()
}

fun View.clickAndGoto(context: Context?, clazz: Class<*>) {
    this.onClick {
        context?.goToActivity(clazz)
    }
}

fun Context.goToActivity(clazz: Class<*>, intentData: HashMap<String, Any?>? = null) {
    val intent = Intent(this, clazz)
    intentData?.let {
        it.forEach { item ->
            when (item.value) {
                is Int -> intent.putExtra(item.key, item.value as Int)
                is Double -> intent.putExtra(item.key, item.value as Double)
                is Float -> intent.putExtra(item.key, item.value as Float)
                else -> intent.putExtra(item.key, item.value.toString())
            }
        }
    }
    startActivity(intent)
}


fun Context.hideKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.isPackageInstalled(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}


