package com.instafinancials.vendoralpha.deleteitlater.data

import com.instafinancials.vendoralpha.deleteitlater.model.Museum

data class MuseumResponse(val status:Int?,val msg:String?,val data:List<Museum>?){
    fun isSuccess():Boolean= (status==200)
}