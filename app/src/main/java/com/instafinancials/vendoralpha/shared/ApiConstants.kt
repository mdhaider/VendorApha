package com.instafinancials.vendoralpha.shared

object ApiConstants {
    const val BASE_URL = "https://apps.instafinancials.com/"
    const val REQUEST_TIMEOUT = 30
    const val RETROFIT_LOG="Retrofit"
    const val ACCESS_TOKEN_KEY= "AccessToken"
    const val APP_VERSION= "AppVersion"
    const val DEV_OS_VERSION="DeviceOSVersion"
    const val SOURCE= "Source"
    const val GST_DATA_ENDPOINT= "InstaGST/v1/json/GSTIN/{GSTIN}"
    const val REQ_OTP_ENDPOINT= "InstaOTP/v1/json/RequestOTP/{MobileNo} "
    const val VERIFY_OTP_ENDPOINT= "InstaOTP/v1/json/VerifyOTP/{OTP}"
    const val GET_USER_ENDPOINT= "InstaUser/v1/json/GetUser/{MobileNo}"
    const val CREATE_ACC_ENDPOINT= "InstaUser/v1/json/RegisterUser/{MobileNo}"
}