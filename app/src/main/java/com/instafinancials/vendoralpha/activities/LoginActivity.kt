package com.instafinancials.vendoralpha.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.JsonObject
import com.instafinancials.vendoralpha.databinding.ActivityLoginBinding
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.CreateAccReq
import com.instafinancials.vendoralpha.models.ReqOtpResponse
import com.instafinancials.vendoralpha.models.UserProfileResponse
import com.instafinancials.vendoralpha.models.VerifyOtpResponse
import com.instafinancials.vendoralpha.network.RetrofitClient
import com.instafinancials.vendoralpha.shared.VendorApp
import com.instafinancials.vendoralpha.shared.hideKeyboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.instafinancials.vendoralpha.R.layout.activity_login)

        binding.mNumber.addTextChangedListener(numberTextChangeListener)
        binding.mOtp.addTextChangedListener(otpTextChangeListener)

        binding.btnReq.setOnClickListener {
            hideKeyboard()
            // showProgress(true)
            verifyOtp(binding.mOtp.text.toString())
        }

        progressDialog =  ProgressDialog(this)
        progressDialog.setMessage("Loading")
        progressDialog.setCancelable(false)

        progressDialog.show()

        createUser()

    }

    private val numberTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length >= 10) {
                checkUserApi(s.toString())
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    private val otpTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length == 6) {
                binding.btnReq.visibility = View.VISIBLE
                binding.btnReq.isEnabled = true
                binding.btnReq.setTextColor(resources.getColor(com.instafinancials.vendoralpha.R.color.white))
            } else {
                binding.btnReq.isEnabled = false
                binding.btnReq.visibility = View.VISIBLE
                binding.btnReq.setTextColor(resources.getColor(com.instafinancials.vendoralpha.R.color.grey_500))
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }


    private fun checkUserApi(mobNumber: String) {
        RetrofitClient.instance.getUserProfile(mobNumber)
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    showToast(t.message!!)
                }

                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    if (response.code() == 200) {
                        val userProfileResponse = response.body()!!

                        if (userProfileResponse.response?.userProfile?.isRegistered!!) {
                            val intent = Intent(VendorApp.instance, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            reqOtp(mobNumber)
                        }

                    } else {
                        showToast(response.body().toString())
                    }

                }
            })
    }

    private fun reqOtp(mobNumber: String) {
        RetrofitClient.instance.reqOtp(mobNumber)
            .enqueue(object : Callback<ReqOtpResponse> {
                override fun onFailure(call: Call<ReqOtpResponse>, t: Throwable) {
                    showToast(t.message!!)
                }

                override fun onResponse(
                    call: Call<ReqOtpResponse>,
                    response: Response<ReqOtpResponse>
                ) {
                    if (response.code() == 200) {
                        val reqOtpResponse = response.body()!!
                        binding.otpParent.visibility = View.VISIBLE

                    } else {
                        showToast(response.body().toString())
                    }

                }
            })
    }

    private fun verifyOtp(mobNumber: String) {
        RetrofitClient.instance.verOtp(mobNumber)
            .enqueue(object : Callback<VerifyOtpResponse> {
                override fun onFailure(call: Call<VerifyOtpResponse>, t: Throwable) {
                    showToast(t.message!!)
                }

                override fun onResponse(
                    call: Call<VerifyOtpResponse>,
                    response: Response<VerifyOtpResponse>
                ) {
                    if (response.code() == 200) {
                        val verifyOtpResponse = response.body()!!
                        startActivity(Intent(VendorApp.instance, MainActivity::class.java))
                        finish()
                    } else {
                        showToast(response.body().toString())
                    }
                }
            })
    }

    private fun createUser() {
        val createAccReq = CreateAccReq()
        createAccReq.userName = "haider"
        createAccReq.userEmail = "nehal.hdr@gmail.com"
        createAccReq.mobileNo = "9962232612"

        val jsonObject = JsonObject()
        jsonObject.addProperty("userName", "haider")
        jsonObject.addProperty("userEmail", "nehal.hdr@gmail.com")
        jsonObject.addProperty("mobileNo", "9962232612")


        RetrofitClient.instance.createAccount(jsonObject, "9962232612")
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    showToast(t.message!!)
                }

                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>) {
                    if (response.code() == 200) {
                        val verifyOtpResponse = response.body()!!
                        startActivity(Intent(VendorApp.instance, MainActivity::class.java))
                        finish()

                        progressDialog.dismiss()
                    } else {
                        showToast(response.body().toString())
                    }
                }
            })
    }
}
