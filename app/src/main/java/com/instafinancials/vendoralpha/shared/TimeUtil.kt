package com.instafinancials.vendoralpha.shared

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object TimeUtil {
    fun stringToDate(datesString: String) {
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        try {
           val date = format.parse(datesString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    fun dateToString(date: Date) {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        try {
            val dateTime = dateFormat.format(date)
            Log.d("Current Date Time", dateTime.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }


    fun getDifferenceDays(d1: Date, d2: Date): Int {
        var daysdiff = 0
        val diff = d2.time - d1.time
        val diffDays = diff / (24 * 60 * 60 * 1000) + 1
        daysdiff = diffDays.toInt()
        return daysdiff
    }
}


