package com.instafinancials.vendoralpha.apicall.model

import com.instafinancials.vendoralpha.apicall.data.OperationCallback

interface MuseumDataSource {
    fun retrieveMuseums(callback: OperationCallback)
    fun cancel()
}