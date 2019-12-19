package com.instafinancials.vendoralpha.shared

import android.app.Application
import com.instafinancials.vendoralpha.BuildConfig
import timber.log.Timber

class VendorApp:Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AppPreferences.init(this)
        instance = this
    }
    companion object {
        lateinit var instance: VendorApp
    }
}