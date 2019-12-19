package com.instafinancials.vendoralpha.apicall.network.apinew

import com.instafinancials.vendoralpha.apicall.network.pojonew.ApiResponse
import com.instafinancials.vendoralpha.apicall.repositories.BaseRepository

class GstRepo(private val apiInterface: GstApiInterface) : BaseRepository() {
    suspend fun getGstDet():ApiResponse?{
        return safeApiCall(
                call = {apiInterface.fetchGstAsync("01AAACA6990Q1ZC").await()},
                error = "Error fetching news"
        )
    }
}