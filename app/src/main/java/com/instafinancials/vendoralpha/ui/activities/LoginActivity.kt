package com.instafinancials.vendoralpha.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.ActivityLoginBinding
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.CreateAccReq
import com.instafinancials.vendoralpha.models.ReqOtpResponse
import com.instafinancials.vendoralpha.models.UserProfileResponse
import com.instafinancials.vendoralpha.models.VerifyOtpResponse
import com.instafinancials.vendoralpha.network.RetrofitClient
import com.instafinancials.vendoralpha.shared.*
import kotlinx.android.synthetic.main.dlg_progress.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dialog: MaterialDialog
    private var mNumber: String? = null
    private var isUserReg: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, com.instafinancials.vendoralpha.R.layout.activity_login
        )

        NetworkLiveData.observe(this, Observer {
            if (it) {
                //connected
            } else {
                binding.root.snack(getString(R.string.no_internet_msg)) {
                    action("Settings", color = R.color.white) {
                        val intent = Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)
                        context.startActivity(intent)
                    }
                }
            }
        })

        binding.mNumber.addTextChangedListener(numberTextChangeListener)
        binding.mOtp.addTextChangedListener(otpTextChangeListener)
        binding.mEmail.addTextChangedListener(mEmailTextChangeListener)

        binding.btnReq.setOnClickListener {
            hideKeyboard()
            dialog.show()
            verifyOtp(binding.mOtp.text.toString())
        }

        binding.btnCreateAc.setOnClickListener {
            dialog.show()
            createUser()
        }

        binding.resendOtp.setOnClickListener {
            reqOtp(mNumber!!)
        }

        dialog = MaterialDialog(this).customView(
            com.instafinancials.vendoralpha.R.layout.dlg_progress,
            scrollable = false
        )
            .cancelable(false)
        val customView = dialog.getCustomView()
        customView.txtTitle.text = getString(com.instafinancials.vendoralpha.R.string.plz_wait)
        //   customView.txtmsg.text=getString(R.string.fetching_data)

        if (AppPreferences.isVerified!!) {
            binding.topPar.visibility = View.GONE
            binding.btnReq.visibility = View.GONE
            binding.creatAccPar.visibility = View.VISIBLE
        }
    }

    private val numberTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length >= 10) {
                dialog.show()
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

    private val mEmailTextChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length > 6) {
                binding.btnCreateAc.visibility = View.VISIBLE
                binding.btnCreateAc.isEnabled = true
                binding.btnCreateAc.setTextColor(resources.getColor(com.instafinancials.vendoralpha.R.color.white))
            } else {
                binding.btnCreateAc.isEnabled = false
                binding.btnCreateAc.visibility = View.VISIBLE
                binding.btnCreateAc.setTextColor(resources.getColor(com.instafinancials.vendoralpha.R.color.grey_500))
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
                    dialog.dismiss()
                }

                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    dialog.dismiss()
                    if (response.code() == 200) {
                        val userProfileResponse = response.body()!!
                        isUserReg = userProfileResponse.response?.userProfile?.isRegistered!!
                        ModelPreferences(application).putObject(
                            Const.PROF_USER,
                            userProfileResponse
                        )
                        reqOtp(mobNumber)

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
                    dialog.dismiss()
                }

                override fun onResponse(
                    call: Call<ReqOtpResponse>,
                    response: Response<ReqOtpResponse>
                ) {
                    dialog.dismiss()
                    if (response.code() == 200) {
                        val reqOtpResponse = response.body()!!
                        if (reqOtpResponse.response?.status == "Success") {
                            binding.otpParent.visibility = View.VISIBLE
                            mNumber = binding.mNumber.text.toString()

                            object : CountDownTimer(60000, 1000) {

                                override fun onTick(millisUntilFinished: Long) {
                                    binding.resendOtp.text =
                                        "Resend Otp in: " + millisUntilFinished / 1000+" "+"sec"
                                    binding.resendOtp.isEnabled = false

                                }

                                override fun onFinish() {
                                    binding.resendOtp.text = "Resend OTP"
                                    binding.resendOtp.isEnabled = true
                                }

                            }.start()
                        }

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
                    dialog.dismiss()
                }

                override fun onResponse(
                    call: Call<VerifyOtpResponse>,
                    response: Response<VerifyOtpResponse>
                ) {
                    dialog.dismiss()
                    if (response.code() == 200) {
                        val verifyOtpResponse = response.body()!!
                        if (verifyOtpResponse.response?.isValid!!) {
                            if (isUserReg) {
                                AppPreferences.isLoggedIn = true
                                startActivity(Intent(VendorApp.instance, MainActivity::class.java))
                                finish()
                            } else {
                                binding.creatAccPar.visibility = View.VISIBLE
                                mNumber = binding.mNumber.text.toString()
                                binding.topPar.visibility = View.GONE
                                binding.btnReq.visibility = View.GONE
                                AppPreferences.isVerified = true
                                binding.tvRegMsg.text="Enter below details to register with us"
                            }
                        } else {
                            showToast(getString(com.instafinancials.vendoralpha.R.string.invalid_otp))
                        }

                    } else {
                        showToast(response.body().toString())
                    }
                }
            })
    }

    private fun createUser() {
        val createAccReq = CreateAccReq()
        createAccReq.userName = binding.mName.text.toString()
        createAccReq.userEmail = binding.mEmail.text.toString()
        createAccReq.mobileNo = mNumber

        RetrofitClient.instance.createAccount(createAccReq, mNumber!!)
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    dialog.dismiss()
                    showToast(t.message!!)
                }

                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    dialog.dismiss()
                    if (response.code() == 200) {
                        val userProfileResponse = response.body()!!
                        if (userProfileResponse.response?.status == "Success") {
                            AppPreferences.isLoggedIn = true
                            ModelPreferences(application).putObject(
                                Const.PROF_USER,
                                userProfileResponse
                            )
                            AppPreferences.isVerified = false
                            startActivity(Intent(VendorApp.instance, MainActivity::class.java))
                            finish()
                        }

                    } else {
                        showToast(response.body().toString())
                    }
                }
            })
    }
}
