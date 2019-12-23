package com.instafinancials.vendoralpha.network

import com.instafinancials.vendoralpha.BuildConfig
import com.instafinancials.vendoralpha.shared.ApiConstants
import com.instafinancials.vendoralpha.shared.ApiConstants.ACCESS_TOKEN_KEY
import com.instafinancials.vendoralpha.shared.ApiConstants.REQUEST_TIMEOUT
import com.instafinancials.vendoralpha.shared.Installation
import com.instafinancials.vendoralpha.shared.VendorApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var logging = HttpLoggingInterceptor(ApiLogger())

    val instance: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader(ACCESS_TOKEN_KEY, Installation.id(VendorApp.instance))
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClient.readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClient.writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        return httpClient.build()
    }

}