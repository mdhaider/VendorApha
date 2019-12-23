package com.instafinancials.vendoralpha.utils

import android.util.Log
import java.lang.Exception

class GSTChecksumUtil {
private val TAG = "GSTChecksum"
    val arr = arrayOf(60,61,62,63,64,65,66,67,70,71, 101, 102, 103, 104, 105, 106, 107, 110, 111, 112, 113, 114, 115,116, 117, 120, 121, 122, 123, 124, 125, 126, 127, 130, 131,132)
    fun checValidGST(gst : String) : Boolean {
        var isValid = false
        var inputGST = gst
        try {
            inputGST = gst.trim().toUpperCase()
            var z = 0
            for(i in 0 until (inputGST.length)) {
                z += step6(inputGST[i])
                println("z=" + z)

            }
            val s = arr.get((36-(z/36))%36)

            val s1 = dto(inputGST[14].toInt())

            println("s= " + s + " s1=" + s1)

            if(s == s1) {
                isValid = true
            }
        } catch (e: Exception) {
            isValid = false
        }
        return isValid
    }

    private fun step6(c: Char) : Int  {
        val o = dto(c.toInt())
        val ind = arr.indexOf(o)
        var  f= 0;
        if(ind%2==0) {
            f = 2
        } else {
            f = 1
        }
        val a = ind*f
        val q = (a/36)
        val r = (a%36)
        Log.d(TAG, "c=" + c + " d=" + c.toInt() + " o=" + o + " index=" + ind + " a=" + a + " a/36=" + q + " a%36=" + r)
        return q + r
    }


    fun dto(d: Int): Int {
        var dc = d
        var o = 0
        var i = 1
        while (dc != 0) {
            o += dc % 8 * i
            dc /= 8
            i *= 10
        }
        return o
    }

}