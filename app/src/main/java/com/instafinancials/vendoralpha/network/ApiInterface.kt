package com.instafinancials.vendoralpha.network

import com.instafinancials.vendoralpha.models.GstResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("InstaGST/v1/json/GSTIN/{GSTIN}")
    fun getGstData(@Path("GSTIN") cin: String): Call<GstResponse>
}