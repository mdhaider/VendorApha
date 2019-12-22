package com.instafinancials.vendoralpha.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.instafinancials.vendoralpha.adapters.DirectorAdapter
import com.instafinancials.vendoralpha.adapters.PotRelAdapter
import com.instafinancials.vendoralpha.adapters.SignatoryAdapter
import com.instafinancials.vendoralpha.databinding.CompanybasicFragmentBinding
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.Director
import com.instafinancials.vendoralpha.models.GstResponse
import com.instafinancials.vendoralpha.models.RelatedParty
import com.instafinancials.vendoralpha.models.Signatory
import com.instafinancials.vendoralpha.viewmodels.DetailViewModel

private const val ARG_PARAM1 = "param1"

class CompanyBasicFragment : Fragment() {
    private lateinit var binding: CompanybasicFragmentBinding
    private lateinit var viewModel: DetailViewModel
    //private lateinit var data: GstResponse
    private lateinit var sigList: ArrayList<Signatory>
    private lateinit var dirList: ArrayList<Director>
    private lateinit var relList: ArrayList<RelatedParty>
    private lateinit var directorAdapter: DirectorAdapter
    private lateinit var signatoryAdapter: SignatoryAdapter
    private lateinit var potRelAdapter: PotRelAdapter
    private lateinit var data: GstResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getSerializable(ARG_PARAM1) as GstResponse
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            com.instafinancials.vendoralpha.R.layout.companybasic_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  data = ModelPreferences(activity!!).getObject(Const.SEARCH_DATA, GstResponse::class.java)!!

        Log.d("gstbasic", data.toString())
        dirList = ArrayList()
        sigList = ArrayList()
        relList = ArrayList()

        setData(data)

        var expand1 = false
        var expand2 = false
        binding.exp1Par.setOnClickListener {
            if (expand1) {
                expand1 = false
                binding.par1.visibility = View.VISIBLE
                binding.exp1.setImageResource(com.instafinancials.vendoralpha.R.drawable.ic_arrow_drop_down_black_24dp)
            } else {
                expand1 = true
                binding.par1.visibility = View.GONE
                binding.exp1.setImageResource(com.instafinancials.vendoralpha.R.drawable.ic_arrow_drop_up_black_24dp)
            }
        }

        binding.exp2Par.setOnClickListener {
            if (expand2) {
                expand2 = false
                binding.par2.visibility = View.VISIBLE
                binding.exp2.setImageResource(com.instafinancials.vendoralpha.R.drawable.ic_arrow_drop_down_black_24dp)
            } else {
                expand2 = true
                binding.par2.visibility = View.GONE
                binding.exp2.setImageResource(com.instafinancials.vendoralpha.R.drawable.ic_arrow_drop_up_black_24dp)
            }
        }
    }

    private fun setData(data: GstResponse) {
        binding.tvLast.text = data.companyMasterSummary?.lastUpdatedDateTime
        binding.TVCinName.text = data.companyMasterSummary?.companyCIN
        binding.tvlatFinan.text = data.companyMasterSummary?.companyLastBsDate
        binding.tvpaidUpCap.text = data.companyMasterSummary?.companyPaidUpCapital
        binding.tvRevrange.text = data.companyMasterSummary?.companyRevenueRange
        binding.tvStatCap.text =
            "${data.companyMasterSummary?.companyRegCity}(${data.companyMasterSummary?.companyRegState})"
        binding.tvWebName.text = data.companyMasterSummary?.companyWebSite

        binding.tvWebName.setOnClickListener {
            goToUrl( data.companyMasterSummary?.companyWebSite!!)
        }

        for (item in data?.directorSignatoryMasterBasic?.directorCurrentMasterBasic?.director!!) {
            dirList.add(item)
        }

        binding.rvDirector.setHasFixedSize(true)
        directorAdapter = DirectorAdapter(dirList)
        binding.rvDirector.adapter = directorAdapter
        binding.rvDirector.layoutManager = LinearLayoutManager(activity)

        for (item in data?.directorSignatoryMasterBasic?.signatoryCurrentMasterBasic?.signatory!!) {
            sigList.add(item)
        }

        binding.rvSignatory.setHasFixedSize(true)
        signatoryAdapter = SignatoryAdapter(sigList)
        binding.rvSignatory.adapter = signatoryAdapter
        binding.rvSignatory.layoutManager = LinearLayoutManager(activity)

        for (item in data.potentialRelatedPartyMasterBasic?.relatedParty!!) {
            relList.add(item)
        }

        binding.rvPotRelParty.setHasFixedSize(true)
        potRelAdapter = PotRelAdapter(relList)
        binding.rvPotRelParty.adapter = potRelAdapter
        binding.rvPotRelParty.layoutManager = LinearLayoutManager(activity)
    }

    private fun goToUrl(url: String) {
        var urlFormed: String? = null
        if (url.startsWith("www")) {
            urlFormed = "https://" + url
        } else if (url.startsWith("https://") || url.startsWith("http://")) {
            urlFormed = url
        } else {
            showToast("Invalid Url")
            return
        }

        val uri = Uri.parse(urlFormed)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: GstResponse) =
            CompanyBasicFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}
