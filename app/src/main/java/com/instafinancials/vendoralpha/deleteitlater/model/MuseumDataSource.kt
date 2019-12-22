package com.instafinancials.vendoralpha.deleteitlater.model

import com.instafinancials.vendoralpha.deleteitlater.data.OperationCallback

interface MuseumDataSource {
    fun retrieveMuseums(callback: OperationCallback)
    fun cancel()
}