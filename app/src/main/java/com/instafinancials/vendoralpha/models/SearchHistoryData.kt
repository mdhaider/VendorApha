package com.instafinancials.vendoralpha.models

import java.io.Serializable

data class SearchHistoryData(
    var cin: String? = null,
    var date: Long? = 0

) : Serializable