package com.instafinancials.vendoralpha.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.hideKeyboard
import com.instafinancials.vendoralpha.onChange
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

 //   private lateinit var binding: ActivityLoginNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
      //  binding = DataBindingUtil.setContentView(this, R.layout.activity_login_new)



    }
}
