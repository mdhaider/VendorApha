package com.instafinancials.vendoralpha.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.GsonBuilder
import com.instafinancials.vendoralpha.BuildConfig
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.adapters.SectionsPagerAdapter
import com.instafinancials.vendoralpha.databinding.FragmentHomeBinding
import com.instafinancials.vendoralpha.db.*
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.GstResponse
import com.instafinancials.vendoralpha.network.RetrofitClient
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.NoConnectivityException
import com.instafinancials.vendoralpha.shared.hideKeyboard
import com.instafinancials.vendoralpha.shared.snack
import com.instafinancials.vendoralpha.ui.activities.CameraActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var gstResponseData: GstResponse
    private var db: AppDatabase? = null
    private var bookDao: BookmarkDataDao? = null
    private var historyDao: HistoryDataDao? = null
    private var gstNo: String? = null
    private var gstData: String? = null
    private var isComingFromBook: Boolean = false
    private var isBookmarked: Boolean = false
    private val gson = GsonBuilder().create()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        binding.bntScan.setOnClickListener(_onItemClicked)
        binding.profile.setOnClickListener(_onItemClicked)
        binding.bookmarkPar.setOnClickListener(_onItemClicked)
        binding.sharePar.setOnClickListener(_onItemClicked)
        binding.trackPar.setOnClickListener(_onItemClicked)
        binding.repPar.setOnClickListener(_onItemClicked)
        binding.advPar.setOnClickListener(_onItemClicked)

        binding.searchView.addTextChangedListener(textChangeListener)

        arguments?.apply {
            gstNo = getString(Const.GST_NUMBER, "")
            gstData = getString(Const.GST_HISTORY, "")
            isComingFromBook = getBoolean(Const.IS_COMING_FROM_BOOKMARK, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItemAtBottom()

        if (!TextUtils.isEmpty(gstNo)) {
            Log.d("data", gstData!!)
            setInitialView()
            gstResponseData = gson.fromJson(gstData, GstResponse::class.java)
            setDataAfterApiCall(gstResponseData)
            binding.searchView.setText(gstNo)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 111) {
            if (data?.extras?.getString(Const.SCAN_DATA) != null) {
                val scanData = data.extras?.getString(Const.SCAN_DATA)
                binding.searchView.setText(scanData)
            } else {
                showToast("Invalid GST")
            }
        }
    }

    private val _onItemClicked = View.OnClickListener {

        when (it.id) {
            R.id.bookmarkPar -> {
                if (isComingFromBook || isBookmarked) {
                    showToast(getString(R.string.already_bookmarked))
                } else {
                    showToast(getString(R.string.item_bookmarked))
                    isBookmarked = true
                    binding.imgBookmark.setImageResource(R.drawable.ic_bookmark_filed)
                    binding.tbBookMark.text = getString(R.string.boomarked_text)
                    addToBookmark()
                }
            }
            R.id.sharePar -> {
                shareSearchedData()
            }
            R.id.trackPar -> {
                showToast("Coming soon!")
            }
            R.id.repPar -> {
                showToast("Coming soon!")
            }
            R.id.advPar -> {
                showToast("Coming soon!")
            }
            R.id.bntScan -> {
                goToCamera()
            }
            R.id.profile -> {
                goToProfile()
            }

            else -> {

            }
        }
    }

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s!!.length >= 15) {
                if (TextUtils.isEmpty(gstNo)) {
                    callAndFetchData(s.toString())
                } else {
                    gstNo = ""
                }
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    private fun shareSearchedData() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "VendorAlpha")
            var shareMessage =
                "\nI searched GST details using Vendor Alpha app.\n" + "GSTIn No: " + gstResponseData.gSTInformationAndCompliance?.gSTRegistrationDetails?.gSTIN + "\n" +
                        "Business name: " + gstResponseData.gSTInformationAndCompliance?.gSTRegistrationDetails?.legalNameOfBusiness
            shareMessage =
                shareMessage + "\n\nClick here to download app:\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share Via"))
        } catch (e: java.lang.Exception) {
        }
    }

    private fun setItemAtBottom() {
        binding.tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                Log.d("pos", position.toString())

                when (position) {
                    0 -> {
                        if (isComingFromBook) {
                            binding.tbBookMark.text = getString(R.string.boomarked_text)
                            binding.imgBookmark.setImageResource(R.drawable.ic_bookmark_filed)
                        } else {
                            binding.tbBookMark.text = getString(R.string.bookmark_msg)
                        }
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

    private fun callAndFetchData(gstNo: String) {
        setInitialView()
        callApi(gstNo)
    }

    private fun setInitialView() {
        hideKeyboard()
        binding.notSearchedView.visibility = View.GONE
        binding.progressBarCyclic.visibility = View.VISIBLE
        binding.dataView.visibility = View.GONE
        binding.bottomView.visibility = View.GONE
    }

    private fun callApi(cinNumber: String) {

        RetrofitClient.instance.getGstData(cinNumber)
            .enqueue(object : Callback<GstResponse> {
                override fun onFailure(call: Call<GstResponse>, t: Throwable) {
                    if(t is NoConnectivityException) {
                      binding.root.snack(R.string.no_internet_msg){}
                    } else{
                        showToast(t.message!!)
                    }
                }

                override fun onResponse(call: Call<GstResponse>, response: Response<GstResponse>) {
                    if (response.code() == 200) {
                        val gstResponse = response.body()!!
                        gstResponseData = gstResponse
                        setDataAfterApiCall(gstResponse)
                    } else {
                        showToast(response.body().toString())
                    }

                }
            })
    }

    private fun setDataAfterApiCall(gstResponse: GstResponse) {
        if (gstResponse.gSTInformationAndCompliance == null) {
            binding.progressBarCyclic.visibility = View.GONE
            binding.notSearchedView.visibility == View.VISIBLE
            showToast("Oops, something went wrong!!")
            return
        }
        setDataInUi(gstResponse)
        addToHistory()
        binding.dataView.visibility = View.VISIBLE
        binding.bottomView.visibility = View.VISIBLE
        binding.progressBarCyclic.visibility = View.GONE

        val adapter =
            SectionsPagerAdapter(activity!!, childFragmentManager)
        adapter.addFragment(GstTrackerFragment.newInstance(gstResponseData), "GST Tracker")

        if (gstResponseData.companyMasterSummary?.companyCIN !=null) {
            adapter.addFragment(CompanyBasicFragment.newInstance(gstResponseData), "CompanyBasic")
            adapter.addFragment(CompanyFinFragment(), "CompanyFin")
        }

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        adapter.notifyDataSetChanged()
        Log.d("data", gstResponse.toString())
        binding.dataView.visibility = View.VISIBLE
        binding.bottomView.visibility = View.VISIBLE
        binding.progressBarCyclic.visibility = View.GONE
    }

    private fun goToProfile() {
        findNavController()
            .navigate(R.id.action_home_to_profile_home)
    }

    private fun goToCamera() {
        startActivityForResult(Intent(context, CameraActivity::class.java), 111)
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

    private fun addToHistory() = GlobalScope.launch(Dispatchers.IO) {
        db = AppDatabase.getAppDataBase(context = activity!!)
        historyDao = db?.historyDataDao()
        val history = HistoryDataForDb(
            0,
            gstResponseData.gSTInformationAndCompliance?.gSTRegistrationDetails?.gSTIN!!,
            gstResponseData.gSTInformationAndCompliance?.gSTRegistrationDetails?.legalNameOfBusiness!!,
            Date(), gstResponseData
        )
        historyDao?.insertHistory(history)
    }

    private fun addToBookmark() = GlobalScope.launch(Dispatchers.IO) {
        db = AppDatabase.getAppDataBase(context = activity!!)
        bookDao = db?.bookmarkDataDao()
        val book = BookmarkDataForDb(
            0,
            gstResponseData.gSTInformationAndCompliance?.gSTRegistrationDetails?.gSTIN!!,
            gstResponseData.gSTInformationAndCompliance?.gSTRegistrationDetails?.legalNameOfBusiness!!,
            Date(), gstResponseData
        )

        bookDao?.insertBookmark(book)
    }

}
