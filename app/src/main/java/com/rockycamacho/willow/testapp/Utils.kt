package com.rockycamacho.willow.testapp

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    val view = when {
        currentFocus != null -> currentFocus
        else -> View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}