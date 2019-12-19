package com.instafinancials.vendoralpha

import android.app.Application
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