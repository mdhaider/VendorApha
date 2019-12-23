package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName


class ReqOtpResponse {
    @SerializedName("Response")
    val response: ResponseSendOtp? = null
}

class ResponseSendOtp {
    @SerializedName("Status")
    val status: String? = null
    @SerializedName("Message")
    val message: String? = null
}