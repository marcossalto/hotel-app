package com.architectcoders.hotelapp.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager

fun Context.toast(message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showDatePickerDialog(supportFragmentManager: FragmentManager, listener: (String) -> Unit) {
    val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
        // +1 because January is zero
        val selectedDate = day.toString() + "/" + (month + 1) + "/" + year
        listener(selectedDate)
    }

    newFragment.show(supportFragmentManager, "datePicker")
}

fun Int.mod9(): Boolean {
    return this % 2 == 0 && this % 3 == 0
}