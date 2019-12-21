package com.instafinancials.vendoralpha.apicall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.instafinancials.vendoralpha.apicall.data.OperationCallback
import com.instafinancials.vendoralpha.apicall.model.MuseumDataSource
import com.instafinancials.vendoralpha.apis.GstResponse

class MuseumViewModel(private val repository: MuseumDataSource):ViewModel() {

    private val _museums = MutableLiveData<GstResponse>()
    val museums: LiveData<GstResponse> = _museums

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    init {
        loadGstData()
    }



    fun loadGstData(){
        _isViewLoading.postValue(true)
        repository.retrieveMuseums(object: OperationCallback {
            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( obj)
            }

            override fun onSuccess(obj: Any?) {
                _isViewLoading.postValue(false)

                if(obj!=null){
                    if(obj==null){
                        _isEmptyList.postValue(true)
                    }else{
                        _museums.value= obj as GstResponse
                    }
                }
            }
        })
    }

}