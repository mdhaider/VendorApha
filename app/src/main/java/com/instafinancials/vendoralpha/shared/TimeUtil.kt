package com.instafinancials.vendoralpha.shared

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object TimeUtil {
    fun stringToDate(datesString: String): String {
        var date1: Date
        var dateTime: String? = null
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val dateFormat = SimpleDateFormat("dd/MM", Locale.ENGLISH)
        try {
            date1 = format.parse(datesString)
            dateTime = dateFormat.format(date1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateTime!!
    }

    fun stringToDate1(datesString: String): Date {
        var date1: Date= Date()
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        try {
            date1 = format.parse(datesString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date1
    }

    fun dateToString(date1: Date): String {
        var dateTime: String? = null
        val dateFormat = SimpleDateFormat("dd/MM", Locale.ENGLISH)
        try {
            dateTime = dateFormat.format(date1)
            Log.d("Current Date Time", dateTime.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateTime!!
    }


    fun getDifferenceDays(d1: Date, d2: Date): Int {
        var daysdiff = 0
        val diff = d2.time - d1.time
        val diffDays = diff / (24 * 60 * 60 * 1000) + 1
        daysdiff = diffDays.toInt()
        return daysdiff
    }
}


