package com.instafinancials.vendoralpha.apicall.model

import android.util.Log
import com.instafinancials.vendoralpha.apicall.data.ApiClient
import com.instafinancials.vendoralpha.apicall.data.OperationCallback
import com.instafinancials.vendoralpha.apis.GstResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG="CONSOLE"

class MuseumRepository(cinNumber:String): MuseumDataSource {

    private var cinNumberNew=cinNumber
    private var call:Call<GstResponse>?=null

    override fun retrieveMuseums(callback: OperationCallback) {
        call= ApiClient.build()?.museums(cin = cinNumberNew)
        call?.enqueue(object :Callback<GstResponse>{
            override fun onFailure(call: Call<GstResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<GstResponse>, response: Response<GstResponse>) {
                response?.body()?.let {
                    if(response.isSuccessful){
                        Log.v(TAG, "data")
                        callback.onSuccess(it)
                    }else{
                        callback.onError(it)
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}