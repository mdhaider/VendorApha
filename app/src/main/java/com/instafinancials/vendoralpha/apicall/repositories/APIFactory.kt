package com.instafinancials.vendoralpha.apicall.repositories

import com.instafinancials.vendoralpha.apicall.network.ewaybillapi.EWayApiHelper
import com.instafinancials.vendoralpha.apicall.network.ewaybillapi.EwayGSTResponse
import com.instafinancials.vendoralpha.apicall.network.homeapi.HomeApiHelper

object APIFactory {
    fun getInstaApiHelper() = HomeApiHelper.getInstance()

    fun gutGetEWayBillHell() = EWayApiHelper.getInstance()
}