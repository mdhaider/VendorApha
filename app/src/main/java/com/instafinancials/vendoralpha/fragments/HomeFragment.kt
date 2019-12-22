package com.instafinancials.vendoralpha.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.adapters.SectionsPagerAdapter
import com.instafinancials.vendoralpha.models.GstResponse
import com.instafinancials.vendoralpha.databinding.FragmentHomeBinding
import com.instafinancials.vendoralpha.deleteitlater.di.Injection
import com.instafinancials.vendoralpha.deleteitlater.viewmodel.MuseumViewModel
import com.instafinancials.vendoralpha.deleteitlater.viewmodel.ViewModelFactory
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.network.RetrofitClient
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.ModelPreferences
import com.instafinancials.vendoralpha.shared.hideKeyboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var viewModel: MuseumViewModel
    private lateinit var binding: FragmentHomeBinding

    companion object {
        const val TAG = "CONSOLE"
        var BaseUrl = "https://apps.instafinancials.com/"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.fab.setOnClickListener {
            goToCamera()
        }

        binding.profile.setOnClickListener {
            goToProfile()
        }

        binding.searchView.addTextChangedListener(textChangeListener)
        binding.bookmarkPar.setOnClickListener(OnItemClicked)
        binding.sharePar.setOnClickListener(OnItemClicked)
        binding.trackPar.setOnClickListener(OnItemClicked)
        binding.repPar.setOnClickListener(OnItemClicked)
        binding.advPar.setOnClickListener(OnItemClicked)

        return binding.root
    }

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s!!.length >= 15) {
                hideKeyboard()
                binding.notSearchedView.visibility = View.GONE
                binding.progressBarCyclic.visibility = View.VISIBLE
                binding.dataView.visibility = View.GONE
                binding.bottomView.visibility = View.GONE
                // setupViewModel(s.toString())
                callApi(s.toString())
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemAtBottom()
    }

    //ui
    private fun setupUI(res: GstResponse) {
        // adapter= MuseumAdapter(viewModel.museums.value?: emptyList())
        //recyclerView.layoutManager= LinearLayoutManager(this)
        //recyclerView.adapter= adapter

        binding.tvComName.text =
            viewModel.museums.value?.gSTInformationAndCompliance?.gSTRegistrationDetails?.legalNameOfBusiness
        binding.tvStatus.text =
            viewModel.museums.value?.gSTInformationAndCompliance?.gSTRegistrationDetails?.gSTNStatus
        binding.tvTaxPayerType.text =
            viewModel.museums.value?.gSTInformationAndCompliance?.gSTRegistrationDetails?.taxpayerType
        binding.tvLocat.text =
            viewModel.museums.value?.gSTInformationAndCompliance?.gSTRegistrationDetails?.registeredState

    }

    private fun setupViewModel(cinNumber: String) {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(Injection.providerRepository(cinNumber))
        ).get(
            MuseumViewModel::class.java
        )
        viewModel.museums.observe(this, renderMuseums)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }


    //observers
    private val renderMuseums = Observer<GstResponse> {
        Log.d("data","data updated ${it.gSTInformationAndCompliance?.gSTRegistrationDetails?.legalNameOfBusiness}")
        //  layoutError.visibility=View.GONE
        // layoutEmpty.visibility=View.GONE
        //adapter.update(it)
        // setupUI()
        binding.dataView.visibility = View.VISIBLE
        binding.bottomView.visibility = View.VISIBLE
        binding.progressBarCyclic.visibility = View.GONE
        ModelPreferences(activity!!).putObject(Const.SEARCH_DATA, it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.d("logging","isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.progressBarCyclic.visibility = visibility
        binding.notSearchedView.visibility = View.GONE
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v("error","onMessageError $it")
        //   layoutError.visibility=View.VISIBLE
        // layoutEmpty.visibility=View.GONE
        //textViewError.text= "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v("empoty","emptyListObserver $it")
        //    layoutEmpty.visibility=View.VISIBLE
        //  layoutError.visibility=View.GONE
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


    private fun setDataInUi(res: GstResponse) {
        binding.tvComName.text =
            res.gSTInformationAndCompliance?.gSTRegistrationDetails?.legalNameOfBusiness
        binding.tvStatus.text =
            res.gSTInformationAndCompliance?.gSTRegistrationDetails?.gSTNStatus
        binding.tvTaxPayerType.text =
            res.gSTInformationAndCompliance?.gSTRegistrationDetails?.taxpayerType
        binding.tvLocat.text =
            res.gSTInformationAndCompliance?.gSTRegistrationDetails?.registeredState
    }

    private fun callApi(cinNumber: String) {
        RetrofitClient.INSTANCE.getGstData(cinNumber)
            .enqueue(object : Callback<GstResponse> {
                override fun onFailure(call: Call<GstResponse>, t: Throwable) {
                    showToast(t.message!!)
                }

                override fun onResponse(call: Call<GstResponse>, response: Response<GstResponse>) {
                    if (response.code() == 200) {
                        val gstResponse = response.body()!!

                        if (gstResponse.gSTInformationAndCompliance == null) {
                            binding.progressBarCyclic.visibility = View.GONE
                            binding.notSearchedView.visibility == View.VISIBLE
                            showToast("Oops, something went wrong!!")
                            return
                        }
                        setDataInUi(gstResponse)
                        binding.dataView.visibility = View.VISIBLE
                        binding.bottomView.visibility = View.VISIBLE
                        binding.progressBarCyclic.visibility = View.GONE
                        ModelPreferences(activity!!).putObject(Const.SEARCH_DATA, gstResponse)

                        val adapter =
                            SectionsPagerAdapter(
                                activity!!,
                                childFragmentManager
                            )
                        adapter.addFragment(GstTrackerFragment(), "GST Tracker")
                        adapter.addFragment(CompanyBasicFragment(), "CompanyBasic")
                        adapter.addFragment(CompanyFinFragment(), "CompanyFin")
                        binding.viewPager.adapter = adapter
                        binding.tabs.setupWithViewPager(binding.viewPager)
                        Log.d("data", gstResponse.toString())
                    } else {
                        showToast(response.body().toString())
                    }

                }
            })
    }

}