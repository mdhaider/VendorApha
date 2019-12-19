package com.instafinancials.vendoralpha.deletellater.network.ewaybillapi

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.instafinancials.vendoralpha.shared.Const.LOG_TAG
import com.instafinancials.vendoralpha.deletellater.repositories.APIFactory
import com.instafinancials.vendoralpha.deletellater.repositories.InstaRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class EwayGSTApi {
    fun validateGSTDetails(gstin : String, listener : InstaRepo.IResponseStateListener) {
        val api = APIFactory.gutGetEWayBillHell().getRetrofit().create<ImageListService>(ImageListService::class.java)
        api.falidateGSTIN(gstin)
            .enqueue(object : Callback<EwayGSTResponse> {
                override fun onFailure(call: Call<EwayGSTResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<EwayGSTResponse>,
                    response: Response<EwayGSTResponse>
                ) {
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

    interface ImageListService {
        @GET("api/gstin/validate")
        fun falidateGSTIN(@Query("gstin") gstin : String) : Call<EwayGSTResponse>
    }
}