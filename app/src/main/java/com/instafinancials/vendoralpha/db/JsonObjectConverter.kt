package com.instafinancials.vendoralpha.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.instafinancials.vendoralpha.models.GstResponse

class JsonObjectConverter {
    @TypeConverter
    fun toStringObj(json: String): GstResponse {
        val type = object : TypeToken<GstResponse>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJsonObject(torrent: GstResponse): String {
        val type = object: TypeToken<GstResponse>() {}.type
        return Gson().toJson(torrent, type)
    }
}