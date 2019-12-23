package com.instafinancials.vendoralpha.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.instafinancials.vendoralpha.BuildConfig
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.ProfileHomeFragmentBinding
import com.instafinancials.vendoralpha.viewmodels.ProfileHomeViewModel

class ProfileHomeFragment : Fragment() {

    private lateinit var viewModel: ProfileHomeViewModel
    private lateinit var binding: ProfileHomeFragmentBinding

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

        binding.topView.setOnClickListener(onItemClicked)
        binding.tvBook.setOnClickListener(onItemClicked)
        binding.tvOrders.setOnClickListener(onItemClicked)
        binding.tvRefer.setOnClickListener(onItemClicked)
        binding.tvSupport.setOnClickListener(onItemClicked)
        binding.tvFeedback.setOnClickListener(onItemClicked)
        binding.tvFree.setOnClickListener(onItemClicked)
        binding.tvTerms.setOnClickListener(onItemClicked)
        binding.tvLogin.setOnClickListener(onItemClicked)
    }

    private val onItemClicked = View.OnClickListener {
        when (it.id) {
            R.id.topView -> {
                findNavController(binding.topView).navigate(R.id.action_prof_prof)
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
                findNavController(binding.tvBook).navigate(R.id.action_prof_login)
            }
        }
    }

    private fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "VendorAlpha")
            var shareMessage = "\nWe are glad to release app! Download the VendorAlpha app to search fro company details.\n\n"
            shareMessage =
                shareMessage+"Click here to download app:\n"+ "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share Via"))
        } catch (e: java.lang.Exception) { //e.toString();
        }
    }
}
