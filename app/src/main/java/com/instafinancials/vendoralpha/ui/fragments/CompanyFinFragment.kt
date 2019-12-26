package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.CompanyfinFragmentBinding
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.viewmodels.LastViewModel
import kotlinx.android.synthetic.main.blank_company_fin.view.*


class CompanyFinFragment : Fragment() {

    private lateinit var viewModel: LastViewModel
    private lateinit var binding: CompanyfinFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.companyfin_fragment, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LastViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.blank.btnNotify.setOnClickListener {
            showToast("Thanks, we will notify you. ")
        }

    }

}
