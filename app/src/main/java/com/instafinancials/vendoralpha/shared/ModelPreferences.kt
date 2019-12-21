package com.instafinancials.vendoralpha.shared

import android.content.Context
import com.google.gson.GsonBuilder

class ModelPreferences(c: Context) {

    private val preferences = c.getSharedPreferences("MODEL_PREFERENCES", Context.MODE_PRIVATE)
    private val editor = preferences.edit()
    private val gson = GsonBuilder().create()

    /**
     * Saves object into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/
    fun <T> putObject(key: String, y: T) {
        //Convert object to JSON String.
        val inString = gson.toJson(y)
        //Save that String in SharedPreferences
        editor.putString(key, inString).commit()
    }

    /**
     * Saves collection of objects into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/

    fun <T> getObject(key: String, c: Class<T>): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        if (value != null) {
            //JSON String was found which means object can be read.
            //We convert this JSON String to model object. Parameter "c" (of type Class<T>" is used to cast.
            return gson.fromJson(value, c)
        } else {
            //No JSON String with this key was found which means key is invalid or object was not saved.
            throw IllegalArgumentException("No object with key: $key was saved")
        }
    }
}