package com.instafinancials.vendoralpha.apicall.data

interface OperationCallback {
    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}