package com.instafinancials.vendoralpha.deleteitlater.data

interface OperationCallback {
    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}