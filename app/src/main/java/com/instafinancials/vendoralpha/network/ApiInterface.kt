package com.instafinancials.vendoralpha.network

import com.google.gson.JsonObject
import com.instafinancials.vendoralpha.models.GstResponse
import com.instafinancials.vendoralpha.models.ReqOtpResponse
import com.instafinancials.vendoralpha.models.UserProfileResponse
import com.instafinancials.vendoralpha.models.VerifyOtpResponse
import com.instafinancials.vendoralpha.shared.ApiConstants.CREATE_ACC_ENDPOINT
import com.instafinancials.vendoralpha.shared.ApiConstants.GET_USER_ENDPOINT
import com.instafinancials.vendoralpha.shared.ApiConstants.GST_DATA_ENDPOINT
import com.instafinancials.vendoralpha.shared.ApiConstants.REQ_OTP_ENDPOINT
import com.instafinancials.vendoralpha.shared.ApiConstants.VERIFY_OTP_ENDPOINT
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET(GST_DATA_ENDPOINT)
    fun getGstData(@Path("GSTIN") cin: String): Call<GstResponse>

    @GET(GET_USER_ENDPOINT)
    fun getUserProfile(@Path("MobileNo") mobNo: String): Call<UserProfileResponse>

    @GET(REQ_OTP_ENDPOINT)
    fun reqOtp(@Path("MobileNo") mobNo: String): Call<ReqOtpResponse>

    @GET(VERIFY_OTP_ENDPOINT)
    fun verOtp(@Path("OTP") otp: String): Call<VerifyOtpResponse>

    @Headers("Content-Type: text/plain")
    @POST(CREATE_ACC_ENDPOINT)
    fun createAccount(@Body createAccReq: JsonObject, @Path("MobileNo") mobNo: String): Call<UserProfileResponse>

}