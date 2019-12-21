package com.instafinancials.vendoralpha.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("InstaGST/v1/json/GSTIN/{GSTIN}")
    fun getGstData(@Path("GSTIN") cin : String): Call<GstResponse>
}
