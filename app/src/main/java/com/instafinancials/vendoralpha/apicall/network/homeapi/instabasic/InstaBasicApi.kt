package com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.instafinancials.vendoralpha.Const.LOG_TAG
import com.instafinancials.vendoralpha.apicall.network.homeapi.HomeApiHelper
import com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.pojo.InstaBasicCINResponse
import com.instafinancials.vendoralpha.apicall.repositories.InstaRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class InstaBasicApi {
    fun fetchImageItems(cin : String, listener : InstaRepo.IResponseStateListener) {
        val api = HomeApiHelper.getInstance().getRetrofit().create<InstaBasicCINService>(InstaBasicCINService::class.java)
        api.fetchInstaBasicCINResponse(cin)
            .enqueue(object : Callback<InstaBasicCINResponse> {
                override fun onFailure(call: Call<InstaBasicCINResponse>, t: Throwable) {
                    Log.e(LOG_TAG,"onFailure :: OOPS!! something went wrong..", t)
                    listener.onError();
                }

                override fun onResponse(call: Call<InstaBasicCINResponse>, response: Response<InstaBasicCINResponse>) {
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
        @GET("api/InstaBasic/V1/json/CompanyCIN/{cin}/all")
        fun fetchInstaBasicCINResponse(@Path("cin") cin : String) : Call<InstaBasicCINResponse>

    }

}