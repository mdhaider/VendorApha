package com.instafinancials.vendoralpha.ui.searchhistory

import java.io.Serializable

data class SearchHistoryData(
    var cin: String? = null,
    var date: Long? = 0

) : Serializable