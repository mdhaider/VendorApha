package com.instafinancials.vendoralpha.deletellater.network.homeapi

import com.google.gson.Gson
import com.instafinancials.vendoralpha.deletellater.USER_KEY
import com.instafinancials.vendoralpha.shared.Const
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeApiHelper private constructor() {
    var logInterceptor = HttpLoggingInterceptor()
    private var mRetrofit: Retrofit

    //creating a Network Interceptor to add api_key in all the request as authInterceptor
    private val mInterceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder().build()
        val request = chain.request()
            .newBuilder()
            .addHeader("AccessTocken", USER_KEY)
            .url(url)
            .build()
        chain.proceed(request)
    }

    private val okHttpClientBuilder = OkHttpClient().newBuilder()

    private var retroClientBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl(Const.BASE_HOME_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())

    init {
        okHttpClientBuilder.addInterceptor(logInterceptor)
            .addInterceptor(mInterceptor)
        var client = okHttpClientBuilder.build()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        mRetrofit = retroClientBuilder.client(client)
            .build()
    }


        companion object {
        private lateinit var mInstance: HomeApiHelper

        public fun getInstance(): HomeApiHelper {
            if (!::mInstance.isInitialized) {
                synchronized(HomeApiHelper::class.java) {
                    if (!::mInstance.isInitialized) {
                        mInstance =
                            HomeApiHelper()
                    }
                }
            }
            return mInstance
        }
    }

    fun getRetrofit(): Retrofit {
        return mRetrofit
    }


}