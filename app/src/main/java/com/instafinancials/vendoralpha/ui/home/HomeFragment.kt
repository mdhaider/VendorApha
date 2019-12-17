package com.instafinancials.vendoralpha.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.instafinancials.vendoralpha.*
import com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.InstaBasicApi
import com.instafinancials.vendoralpha.apicall.repositories.InstaRepo
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var searchView: EditText
    private lateinit var fab: ImageView
    private lateinit var prof: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = root.findViewById(R.id.view_pager)
        tabs = root.findViewById(R.id.tabs)
        searchView = root.findViewById(R.id.searchView)
        fab = root.findViewById(R.id.fab)
        prof = root.findViewById(R.id.profile)

        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if (s!!.length >= 15) {
                    InstaBasicApi().fetchImageItems(
                        "U72501KA2016PTC092387",
                        object : InstaRepo.IResponseStateListener {
                            override fun onSuccess() {
                                Timber.d("success")
                            }

                            override fun onError() {
                            }
                        })
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        fab.setOnClickListener {
            goTocamera()
        }

        prof.setOnClickListener {
            goToProfile()
        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            SectionsPagerAdapter(
                activity!!,
                childFragmentManager)
        adapter.addFragment(GstTrackerFragment(), "GST Tracker")
        adapter.addFragment(InstaBasicFragment(), "CompanyBasic")
        adapter.addFragment(InstaSummaryFragment(), "CompanyFin")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        searchView.setText(AppPreferences.gstNum)
    }

    private fun goToProfile() {
        NavHostFragment.findNavController(this).navigate(R.id.action_home_to_profile_home)
    }

    private fun goTocamera() {
        NavHostFragment.findNavController(this).navigate(R.id.action_home_to_scan)
    }
}