package com.instafinancials.vendoralpha.apicall.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.instafinancials.vendoralpha.apicall.network.models.RequestState

class InstaRepo {

    val stateLiveData =  MutableLiveData<RequestState>()

    private fun getRequestState() : LiveData<RequestState> {
        return stateLiveData
    }

    interface IResponseStateListener {
        fun onSuccess()
        fun onError()
    }

}