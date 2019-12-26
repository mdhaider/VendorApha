package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class VerifyOtpResponse : Serializable {
    @SerializedName("Response")
    val response: ResponseVerifyOtp? = null
}

class ResponseVerifyOtp : Serializable {
    @SerializedName("IsValid")
    val isValid: Boolean? = false
    @SerializedName("Message")
    val message: String? = null
}
