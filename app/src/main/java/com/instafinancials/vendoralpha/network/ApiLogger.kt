package com.instafinancials.vendoralpha.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.instafinancials.vendoralpha.shared.ApiConstants.RETROFIT_LOG
import okhttp3.logging.HttpLoggingInterceptor

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {

        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                    .create().toJson(JsonParser().parse(message))
                Log.d(RETROFIT_LOG, prettyPrintJson)
            } catch (m: JsonSyntaxException) {
                Log.d(RETROFIT_LOG, message)
            }
        } else {
            Log.d(RETROFIT_LOG, message)
            return
        }
    }
}