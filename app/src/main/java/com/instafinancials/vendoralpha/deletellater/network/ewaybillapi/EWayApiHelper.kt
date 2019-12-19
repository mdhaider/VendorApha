package com.instafinancials.vendoralpha.deletellater.network.ewaybillapi

import com.google.gson.Gson
import com.instafinancials.vendoralpha.shared.Const
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EWayApiHelper private constructor() {
    //creating a Network Interceptor to add api_key in all the request as authInterceptor
    private var mRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl(Const.BASE_EWAY_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    companion object {
        private lateinit var mInstance: EWayApiHelper

        public fun getInstance(): EWayApiHelper {
            if (!::mInstance.isInitialized) {
                synchronized(EWayApiHelper::class.java) {
                    if (!::mInstance.isInitialized) {
                        mInstance =
                            EWayApiHelper()
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