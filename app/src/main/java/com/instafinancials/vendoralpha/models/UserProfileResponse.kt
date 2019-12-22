package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName


class UserProfileResponse {
    @SerializedName("Response")
    val response: ResponseGetUser? = null
}

class ResponseGetUser {
    @SerializedName("Status")
    val status: String? = null
    @SerializedName("Message")
    val message: String? = null
    @SerializedName("UserProfile")
    val userProfile: UserProfile? = null
}

class UserProfile {
    @SerializedName("UserName")
    val userName: String? = null
    @SerializedName("UserEmail")
    val userEmail: String? = null
    @SerializedName("MobileNo")
    val mobileNo: String? = null
    @SerializedName("IsRegistered")
    val isRegistered: Boolean? = false
    @SerializedName("TokenAccess")
    val tokenAccess: Boolean? = false
    @SerializedName("TokenStatus")
    val tokenStatus: Boolean? = null
    @SerializedName("ActivatedDate")
    val activatedDate: String? = null
    @SerializedName("ExpiryDate")
    val expiryDate: String? = null
    @SerializedName("DailyCallLimit")
    val dailyCallLimit: Int? = 0
    @SerializedName("DailyCallConsumed")
    val dailyCallConsumed: Int? = 0
    @SerializedName("TotalLimit")
    val totalLimit: Int? = 0
    @SerializedName("TotalConsumed")
    val totalConsumed: Int? = 0
    @SerializedName("IsGSTAllowed")
    val isGSTAllowed: Boolean? = false
    @SerializedName("IsSummaryAllowed")
    val isSummaryAllowed: Boolean? = false
}