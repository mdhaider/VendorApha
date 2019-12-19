package com.instafinancials.vendoralpha.deletellater.network

import com.instafinancials.vendoralpha.deletellater.network.models.LatestNews
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  NewsApiInterface{
    @GET("v2/everything")
    fun fetchLatestNewsAsync(@Query("q") query: String,
                             @Query("sortBy") sortBy : String) : Deferred<Response<LatestNews>>
}