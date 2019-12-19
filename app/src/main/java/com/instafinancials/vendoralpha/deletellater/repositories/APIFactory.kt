package com.instafinancials.vendoralpha.deletellater.repositories

import com.instafinancials.vendoralpha.deletellater.network.ewaybillapi.EWayApiHelper
import com.instafinancials.vendoralpha.deletellater.network.homeapi.HomeApiHelper

object APIFactory {
    fun getInstaApiHelper() = HomeApiHelper.getInstance()

    fun gutGetEWayBillHell() = EWayApiHelper.getInstance()
}