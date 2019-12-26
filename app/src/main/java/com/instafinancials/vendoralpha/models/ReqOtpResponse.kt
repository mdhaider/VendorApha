package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ReqOtpResponse : Serializable {
    @SerializedName("Response")
    val response: ResponseSendOtp? = null
}

class ResponseSendOtp : Serializable {
    @SerializedName("Status")
    val status: String? = null
    @SerializedName("Message")
    val message: String? = null
}