package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.adapters.SectionsPagerAdapter1
import com.instafinancials.vendoralpha.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter1(activity!!, childFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout =binding.tabs
        tabs.setupWithViewPager(viewPager)


    }
}