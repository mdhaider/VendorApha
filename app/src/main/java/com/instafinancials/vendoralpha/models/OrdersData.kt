package com.instafinancials.vendoralpha.models

import java.io.Serializable

data class OrdersData(
    var companyName: String? = null,
    var orderedReport: String? = null,
    var orderedDate: Long? = 0,
    var orderedStatus: String? = null,
    var deliveredDate: Long? = 0

) : Serializable