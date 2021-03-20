package com.architectcoders.hotelapp.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun Context.toast(message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
        val selectedDate = String.format("%02d", day) + "/" + String.format("%02d", (month + 1)) + "/" + year
        listener(selectedDate)
    }

    newFragment.show(supportFragmentManager, "datePicker")
}

fun Int.mod9(): Boolean {
    return this % 2 == 0 && this % 3 == 0
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade()).into(this)
}

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}

val String.capitalizeWords
    get() = this.toLowerCase().split(" ").joinToString(" ") { it.capitalize() }

fun String.toISODate(): String {
    with(this){
        val day = substring(0, 2)
        val mon = substring(3, 5)
        val year = substring(6, 10)
        return "$year-$mon-$day"
    }
}
