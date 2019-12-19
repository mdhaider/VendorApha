package com.instafinancials.vendoralpha.apicall.network.apinew

import com.instafinancials.vendoralpha.apicall.network.pojonew.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  GstApiInterface{
    @GET("InstaGST/v1/json/GSTIN")
    fun fetchGstAsync(@Query("GSTIN") gstTin : String) : Deferred<Response<ApiResponse>>
}