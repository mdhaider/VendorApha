
package com.instafinancials.vendoralpha.shared

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = Const.PREF_NAME
    private const val MODE = Const.PRIVATE_MODE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_VERIFIED = Pair("is_verified", false)
    private val IS_LOGGED_IN = Pair("is_logged_in", false)


    fun init(context: Context) {
        preferences = context.getSharedPreferences(
            NAME,
            MODE
        )
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isVerified: Boolean?
        get() = preferences.getBoolean(
            IS_VERIFIED.first, IS_VERIFIED.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_VERIFIED.first, value!!)
        }

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(
            IS_LOGGED_IN.first, IS_LOGGED_IN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGGED_IN.first, value)
        }

}