package com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.instafinancials.vendoralpha.Const.LOG_TAG
import com.instafinancials.vendoralpha.apicall.network.homeapi.HomeApiHelper
import com.instafinancials.vendoralpha.apicall.network.pojonew2.ApiResponseNew

import com.instafinancials.vendoralpha.apicall.repositories.InstaRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class InstaBasicApi {
    fun fetchImageItems(cin : String, listener : InstaRepo.IResponseStateListener) {
        val api = HomeApiHelper.getInstance().getRetrofit().create<InstaBasicCINService>(InstaBasicCINService::class.java)
        api.fetchApiResponseNew(cin)
            .enqueue(object : Callback<ApiResponseNew> {
                override fun onFailure(call: Call<ApiResponseNew>, t: Throwable) {
                    Log.e(LOG_TAG,"onFailure :: OOPS!! something went wrong..", t)
                    listener.onError();
                }

                override fun onResponse(call: Call<ApiResponseNew>, response: Response<ApiResponseNew>) {
                    Log.d(LOG_TAG,"onResponse :" + response.body().toString())
                    when (response.code()) {
                        200 -> {
                            Thread(Runnable {
                                Log.d(LOG_TAG,response.body().toString())
                                // do db operations for responses if required
                                val handler = Handler(Looper.getMainLooper())
                                handler.post {
                                    listener.onSuccess()
                                }
                            }).start()
                        } else ->
                        listener.onError()
                    }
                }

            })
    }

    interface InstaBasicCINService {
        @GET("InstaGST/v1/json/GSTIN/{GSTIN}")
        fun fetchApiResponseNew(@Path("GSTIN") cin : String) : Call<ApiResponseNew>

    }

}