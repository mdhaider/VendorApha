package com.instafinancials.vendoralpha.shared

import android.app.Application


class VendorApp:Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        instance = this

    }
    companion object {
        lateinit var instance: VendorApp
    }
}