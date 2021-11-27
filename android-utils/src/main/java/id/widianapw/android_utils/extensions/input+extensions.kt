package id.widianapw.android_utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import id.widianapw.android_utils.common.Const
import id.widianapw.android_utils.validator.ValidatorFactory
import id.widianapw.android_utils.validator.ValidatorResult
import id.widianapw.android_utils.validator.ValidatorType

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

fun EditText.onTextChanged(onTextChanged: (String)->Unit){
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    })
}

fun TextInputLayout.value(): String{
    return this.editText?.text.toString()
}


fun TextInputLayout.validate(vararg types: ValidatorType): Boolean {
    listOf(this).removeError()
    val value = this.value()
    val validationResults = mutableListOf<ValidatorResult>()
    run loop@{
        types.forEach { type ->
            val result = ValidatorFactory.validate(value, type)
            validationResults.add(result)
            if (!result.isSuccess) {
                return@loop
            }
        }
    }
    val isSuccess = validationResults.filter { !it.isSuccess }.count() == 0
    if (!isSuccess) {
        this.error = validationResults.findLast { !it.isSuccess }?.message
        this.isErrorEnabled = true
    }
    return isSuccess
}


fun List<TextInputLayout>.removeError(){
    this.forEach {
        it.editText?.onTextChanged { _->
            it.error = null
        }
    }
}

fun EditText?.openTimePicker(fm: FragmentManager) {
    //variable to set selected time on time picker dialog
    val initialValue = this?.text.toString()
    val selectedTime = if (initialValue.isEmpty()) "00:00" else initialValue
    val hour = selectedTime.split(":")[0].toIntOrNull()
    val minute = selectedTime.split(":")[1].toIntOrNull()
    //time picker dialog builder
    val timePicker =
        MaterialTimePicker.Builder()
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setTimeFormat(
                TimeFormat.CLOCK_24H
            ).setHour(hour ?: 0).setMinute(minute ?: 0).build()
    timePicker.addOnPositiveButtonClickListener {
        val value = "${getTimeStringFormat(timePicker.hour.toString())}:${
            getTimeStringFormat(timePicker.minute.toString())
        }"
        this?.setText(value)
    }
    timePicker.show(fm, "")
}

fun EditText?.openDatePicker(fm: FragmentManager) {
    val selectedDateMillis =
        this?.text.toString()
            .toDateInMillis(Const.READ_DATE_FORMAT, isConvertToLocal = false)
    val datePicker =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(
                selectedDateMillis
            )
            .build()
    datePicker.addOnPositiveButtonClickListener {
        val formattedDate = it.toDate(Const.READ_DATE_FORMAT)
        this?.setText(formattedDate)
    }
    datePicker.show(fm, "")
}

private fun getTimeStringFormat(time: String): String {
    var temp = time
    if (time.length == 1) temp = "0$time"
    return temp
}

fun AutoCompleteTextView.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    })
}