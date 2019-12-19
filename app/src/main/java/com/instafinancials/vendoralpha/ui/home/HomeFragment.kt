package com.instafinancials.vendoralpha.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.instafinancials.vendoralpha.*
import com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.InstaBasicApi
import com.instafinancials.vendoralpha.apicall.repositories.InstaRepo
import com.instafinancials.vendoralpha.databinding.FragmentHomeBinding
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if (s!!.length >= 15) {
                    InstaBasicApi().fetchImageItems(
                        "01AAACA6990Q1ZC",
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

        binding.fab.setOnClickListener {
            goToCamera()
        }

        binding.profile.setOnClickListener {
            goToProfile()
        }

        binding.bookmarkPar.setOnClickListener(OnItemClicked)
        binding.sharePar.setOnClickListener(OnItemClicked)
        binding.trackPar.setOnClickListener(OnItemClicked)
        binding.repPar.setOnClickListener(OnItemClicked)
        binding.advPar.setOnClickListener(OnItemClicked)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            SectionsPagerAdapter(
                activity!!,
                childFragmentManager
            )
        adapter.addFragment(GstTrackerFragment(), "GST Tracker")
        adapter.addFragment(InstaBasicFragment(), "CompanyBasic")
        adapter.addFragment(InstaSummaryFragment(), "CompanyFin")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.searchView.setText(AppPreferences.gstNum)
        setItemAtBottom()

        binding.dataView.visibility=View.GONE
        binding.progressBarCyclic.visibility=View.VISIBLE

        getApi()
    }

    private fun getApi() {
        InstaBasicApi().fetchImageItems(
            "01AAACA6990Q1ZC",
            object : InstaRepo.IResponseStateListener {
                override fun onSuccess() {
                    Timber.d("success")
                    binding.dataView.visibility=View.VISIBLE
                    binding.progressBarCyclic.visibility=View.GONE

                }

                override fun onError() {
                    binding.progressBarCyclic.visibility=View.GONE
                }
            })
    }



    private fun goToProfile() {
        NavHostFragment.findNavController(this).navigate(R.id.action_home_to_profile_home)
    }

    private fun goToCamera() {
        NavHostFragment.findNavController(this).navigate(R.id.action_home_to_scan)
    }

    private fun setItemAtBottom() {
        binding.tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                Log.d("pos", position.toString())

                when (position) {
                    0 -> {
                        binding.trackPar.visibility = View.VISIBLE
                        binding.repPar.visibility = View.GONE
                        binding.advPar.visibility = View.GONE

                    }
                    1 -> {
                        binding.trackPar.visibility = View.GONE
                        binding.repPar.visibility = View.VISIBLE
                        binding.advPar.visibility = View.GONE

                    }
                    else -> {
                        binding.trackPar.visibility = View.GONE
                        binding.repPar.visibility = View.GONE
                        binding.advPar.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private val OnItemClicked = View.OnClickListener {

        when (it.id) {
            R.id.bookmarkPar -> {
                showToast("Item bookmarked")
            }
            R.id.sharePar -> {
                showToast("Item Shared")
            }
            R.id.trackPar -> {
                showToast("Item Tracked")
            }
            R.id.repPar -> {
                showToast("Get fin report")
            }
            R.id.advPar -> {
                showToast("Get advanced report")
            }

            else -> {

            }
        }


    }

}