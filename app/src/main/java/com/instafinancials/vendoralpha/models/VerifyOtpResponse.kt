package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName


class VerifyOtpResponse {
    @SerializedName("Response")
    val response: ResponseVerifyOtp? = null
}

class ResponseVerifyOtp {
    @SerializedName("IsValid")
    val isValid: Boolean? = false
    @SerializedName("Message")
    val message: String? = null
}
