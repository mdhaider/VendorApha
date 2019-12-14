package com.instafinancials.vendoralpha.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.ActivityLoginBinding
import com.instafinancials.vendoralpha.hideKeyboard
import com.instafinancials.vendoralpha.onChange

class LoginActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.mNumber.onChange {
            if (it.length == 10) {

            }
        }

        binding.mPass.onChange {
            if (it.length > 5) {
                binding.btnReq.isEnabled = true
                binding.btnReq.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.btnReq.isEnabled = false
            }
        }

        binding.btnReq.setOnClickListener {
            hideKeyboard()
           // showProgress(true)
        }

    }
}
