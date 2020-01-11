package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.FragmentSupportBinding
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.viewmodels.SupportViewModel


class SupportFragment : Fragment() {

    private lateinit var sendViewModel: SupportViewModel
    private lateinit var binding:FragmentSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_support, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_support_profilehome)
        }

        sendViewModel =
            ViewModelProviders.of(this).get(SupportViewModel::class.java)

        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        binding.webview.loadUrl(Const.SUPPORT_URL)
    }
}