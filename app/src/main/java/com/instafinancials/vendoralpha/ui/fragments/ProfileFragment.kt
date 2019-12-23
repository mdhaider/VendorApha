package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.FragmentProfileBinding
import com.instafinancials.vendoralpha.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        profileViewModel.email.observe(this, Observer {
            binding.mEmail.setText(it)
        })

        profileViewModel.phone.observe(this, Observer {
            binding.mPhone.setText(it)
        })

        profileViewModel.state.observe(this, Observer {
            binding.mState.setText(it)
        })

        profileViewModel.country.observe(this, Observer {
            binding.mCountry.setText(it)
        })


    }
}