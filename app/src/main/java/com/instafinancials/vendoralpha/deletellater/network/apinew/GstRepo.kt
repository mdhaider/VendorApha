package com.instafinancials.vendoralpha.deletellater.network.apinew

import com.instafinancials.vendoralpha.deletellater.network.pojonew.ApiResponse
import com.instafinancials.vendoralpha.deletellater.repositories.BaseRepository

class GstRepo(private val apiInterface: GstApiInterface) : BaseRepository() {
    suspend fun getGstDet():ApiResponse?{
        return safeApiCall(
                call = {apiInterface.fetchGstAsync("01AAACA6990Q1ZC").await()},
                error = "Error fetching news"
        )
    }
}