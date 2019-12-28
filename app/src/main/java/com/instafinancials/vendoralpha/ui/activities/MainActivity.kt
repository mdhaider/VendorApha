package com.instafinancials.vendoralpha.ui.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.ActivityMainBinding
import com.instafinancials.vendoralpha.extensions.action
import com.instafinancials.vendoralpha.extensions.snack
import com.instafinancials.vendoralpha.shared.NetworkLiveData
import eu.dkaratzas.android.inapp.update.Constants
import eu.dkaratzas.android.inapp.update.InAppUpdateManager


class MainActivity : AppCompatActivity() {
    private var inAppUpdateManager: InAppUpdateManager? = null
    private val REQ_CODE_VERSION_UPDATE = 530
    private var mSnackbar: Snackbar? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        inAppUpdateManager = InAppUpdateManager.Builder(this, REQ_CODE_VERSION_UPDATE)
            .resumeUpdates(true) // Resume the update, if the update was stalled. Default is true
            .mode(Constants.UpdateMode.FLEXIBLE)

        inAppUpdateManager!!.checkForAppUpdate()

        mSnackbar =
            Snackbar.make(binding.root, getString(R.string.press_again), Snackbar.LENGTH_SHORT)

        NetworkLiveData.observe(this, Observer {
            if (it) {
                //connected
            } else {
                binding.root.snack(getString(R.string.no_internet_msg)) {
                    action("Settings", color = R.color.white) {
                        val intent = Intent(ACTION_DATA_ROAMING_SETTINGS)
                        context.startActivity(intent)
                    }
                }
            }
        })
    }

    private fun navController() =
        Navigation.findNavController(this, R.id.nav_host_fragment)

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        when (navController().currentDestination?.id) {
            R.id.nav_home -> {
                if (mSnackbar!!.isShown) {
                    super.onBackPressed()
                } else {
                    mSnackbar!!.show()
                }
            }
            else -> {
                onSupportNavigateUp()
            }
        }
    }
}
