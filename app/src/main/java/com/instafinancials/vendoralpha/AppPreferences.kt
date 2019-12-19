
package com.instafinancials.vendoralpha

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = Const.PREF_NAME
    private const val MODE = Const.PRIVATE_MODE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val GST_NUM = Pair("gst_num", "")


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var gstNum: String?
        get() = preferences.getString(GST_NUM.first, GST_NUM.second)
        set(value) = preferences.edit {
            it.putString(GST_NUM.first, value)
        }

}