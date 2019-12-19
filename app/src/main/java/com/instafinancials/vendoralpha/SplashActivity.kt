package com.instafinancials.vendoralpha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        decisionToGo()
    }

    private fun decisionToGo() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}