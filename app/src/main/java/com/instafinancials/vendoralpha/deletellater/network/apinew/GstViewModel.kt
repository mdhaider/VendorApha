package com.instafinancials.vendoralpha.deletellater.network.apinew

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.instafinancials.vendoralpha.deletellater.network.pojonew.ApiResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class GstViewModel : ViewModel(){
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val gstRepository : GstRepo = GstRepo(GstApiService.gstApi)
     val gstLiveData = MutableLiveData<ApiResponse>()
    fun getGst() {
        scope.launch {
            val gstDet = gstRepository.getGstDet()
            gstLiveData.postValue(gstDet)

        }
    }
    fun cancelRequests() = coroutineContext.cancel()
}