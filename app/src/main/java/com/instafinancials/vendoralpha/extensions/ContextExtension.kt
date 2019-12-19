package com.instafinancials.vendoralpha.extensions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, text, duration).show()
}

fun Activity.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}