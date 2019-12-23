package com.instafinancials.vendoralpha.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.instafinancials.vendoralpha.BuildConfig
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.activities.LoginActivity
import com.instafinancials.vendoralpha.activities.MainActivity
import com.instafinancials.vendoralpha.databinding.ProfileHomeFragmentBinding
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.UserProfileResponse
import com.instafinancials.vendoralpha.shared.AppPreferences
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.ModelPreferences
import com.instafinancials.vendoralpha.shared.VendorApp
import com.instafinancials.vendoralpha.viewmodels.ProfileHomeViewModel

class ProfileHomeFragment : Fragment() {

    private lateinit var viewModel: ProfileHomeViewModel
    private lateinit var binding: ProfileHomeFragmentBinding
    private lateinit var user: UserProfileResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.profile_home_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileHomeViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(AppPreferences.isLoggedIn){
            binding.tvLogin.setText("Log out")
            binding.topView.setOnClickListener(onItemClicked)
        } else{
            binding.tvLogin.setText("Log In")
        }

        binding.tvHistory.setOnClickListener(onItemClicked)
        binding.tvBook.setOnClickListener(onItemClicked)
        binding.tvOrders.setOnClickListener(onItemClicked)
        binding.tvRefer.setOnClickListener(onItemClicked)
        binding.tvSupport.setOnClickListener(onItemClicked)
        binding.tvFeedback.setOnClickListener(onItemClicked)
        binding.tvFree.setOnClickListener(onItemClicked)
        binding.tvTerms.setOnClickListener(onItemClicked)
        binding.tvLogin.setOnClickListener(onItemClicked)

        if (AppPreferences.isLoggedIn) {
            user = ModelPreferences(activity!!).getObject(
                Const.PROF_USER,
                UserProfileResponse::class.java
            )!!

            if (!TextUtils.isEmpty(user.response?.userProfile?.userName)) {
                binding.tvName.text = user.response?.userProfile?.userName
                binding.showProfView.visibility = View.VISIBLE
                binding.tvName.setTextColor(resources.getColor(R.color.white))

            } else {
                binding.tvName.text = "Guest User"
                binding.tvName.setBackgroundResource(R.drawable.rect_box_color)
            }

        } else {
            binding.tvName.text = "Guest User"
            binding.tvName.setBackgroundResource(R.drawable.rect_box_color)
        }
    }

    private val onItemClicked = View.OnClickListener {
        when (it.id) {
            R.id.topView -> {
                goToProfile(user.response?.userProfile?.mobileNo!!)
            }
            R.id.tvHistory -> {
                findNavController(binding.tvHistory).navigate(R.id.action_prof_history)
            }
            R.id.tvBook -> {
                findNavController(binding.tvBook).navigate(R.id.action_prof_book)
            }
            R.id.tvOrders -> {
                findNavController(binding.tvBook).navigate(R.id.action_prof_orders)
            }
            R.id.tvRefer -> {
                shareApp()
            }
            R.id.tvSupport -> {
                findNavController(binding.tvBook).navigate(R.id.action_prof_support)
            }
            R.id.tvFeedback -> {
                findNavController(binding.tvBook).navigate(R.id.action_prof_feedback)
            }
            R.id.tvFree -> {
                findNavController(binding.tvBook).navigate(R.id.action_prof_free)
            }
            R.id.tvTerms -> {
                findNavController(binding.tvBook).navigate(R.id.action_prof_terms)
            }
            R.id.tvLogin -> {
                if (AppPreferences.isLoggedIn) {
                    showlogoutDialog()
                } else {
                    startActivity(Intent(VendorApp.instance, LoginActivity::class.java))
                    activity!!.finish()
                }

            }
        }
    }

    private fun goToProfile(mobNo: String) {
        val bundle = Bundle().apply {
            putString(Const.MOB_NO, mobNo)
        }

        findNavController(binding.topView).navigate(R.id.action_prof_prof, bundle)
    }

    private fun showlogoutDialog() {
        MaterialDialog(activity!!).show {
            message(R.string.logout_msg)
            positiveButton(R.string.logout_pos) { dialog ->
                logOut()
            }
            negativeButton(R.string.logout_neg) { dialog ->
                dialog.dismiss()
            }
        }
    }

    private fun logOut() {
        try {
            AppPreferences.isLoggedIn = false
            ModelPreferences(activity!!).putObject(Const.PROF_USER, null)
            showToast("You are Signed out!")
            val intent = Intent(activity!!, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity!!.finish()

        } catch (e: Exception) {
            Log.e("ProfileFrag", "onClick: Exception " + e.message, e)
        }
    }

    private fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "VendorAlpha")
            var shareMessage =
                "\nWe are glad to release app! Download the VendorAlpha app to search fro company details.\n\n"
            shareMessage =
                shareMessage + "Click here to download app:\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share Via"))
        } catch (e: java.lang.Exception) { //e.toString();
        }
    }
}
