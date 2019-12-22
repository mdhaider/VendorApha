package com.instafinancials.vendoralpha.network

import com.instafinancials.vendoralpha.models.GstResponse
import com.instafinancials.vendoralpha.shared.ApiConstants.GST_DATA_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET(GST_DATA_ENDPOINT)
    fun getGstData(@Path("GSTIN") cin: String): Call<GstResponse>
}