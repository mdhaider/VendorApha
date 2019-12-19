package com.instafinancials.vendoralpha.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.ActivityLoginBinding
import com.instafinancials.vendoralpha.shared.hideKeyboard
import com.instafinancials.vendoralpha.extensions.onChange

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.mNumber.onChange {
            if (it.length == 10) {
                binding.otpParent.visibility = View.VISIBLE
            }
        }

        binding.mOtp.onChange {
            if (it.length ==4) {
                binding.btnReq.visibility=View.VISIBLE
                binding.btnReq.isEnabled = true
                binding.btnReq.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.btnReq.isEnabled = false
                binding.btnReq.visibility=View.VISIBLE
                binding.btnReq.setTextColor(resources.getColor(R.color.grey_500))
            }
        }

        binding.btnReq.setOnClickListener {
            hideKeyboard()
            // showProgress(true)
        }

    }
}
