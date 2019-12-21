package com.instafinancials.vendoralpha.apicall.data

import com.instafinancials.vendoralpha.apicall.model.Museum

data class MuseumResponse(val status:Int?,val msg:String?,val data:List<Museum>?){
    fun isSuccess():Boolean= (status==200)
}