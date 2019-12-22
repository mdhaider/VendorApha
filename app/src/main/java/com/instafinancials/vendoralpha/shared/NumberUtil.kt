package com.instafinancials.vendoralpha.shared

import kotlin.math.pow
import kotlin.math.roundToInt

object NumberUtil {
    fun numberTextFormat(n: Long): String {
        val c = arrayOf("K", "L", "Cr")
        val size = n.toString().length
        when {
            size in 4..5 -> {
                val value = 10.0.pow(1.0).toInt()
                val d = (n / 1000.0 * value).roundToInt().toDouble() / value
                return ((n / 1000.0 * value).roundToInt().toDouble() / value).toString() + " " + c[0]
            }
            size in 6..7 -> {
                val value = 10.0.pow(1.0).toInt()
                return ((n / 100000.0 * value).roundToInt().toDouble() / value).toString() + " " + c[1]
            }
            size >= 8 -> {
                val value = 10.0.pow(1.0).toInt()
                return ((n / 10000000.0 * value).roundToInt().toDouble() / value).toString() + " " + c[2]
            }
            else -> return n.toString() + ""
        }
    }
}