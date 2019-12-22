package com.instafinancials.vendoralpha.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.adapters.GstFilingAdapter
import com.instafinancials.vendoralpha.adapters.GstFilingDetailAdapter
import com.instafinancials.vendoralpha.databinding.GsttrackerFragmentBinding
import com.instafinancials.vendoralpha.models.GSTComplianceRecord
import com.instafinancials.vendoralpha.models.GSTSingleRecord
import com.instafinancials.vendoralpha.models.GstResponse
import com.instafinancials.vendoralpha.viewmodels.BasicViewModel
import kotlinx.android.synthetic.main.custom_view_2.view.*

class GstTrackerFragment : Fragment() {

    private lateinit var viewModel: BasicViewModel
    private lateinit var binding: GsttrackerFragmentBinding
  //  private lateinit var data: GstResponse
    private lateinit var data: GstResponse
    private lateinit var adapter: GstFilingAdapter
    private lateinit var adapter1: GstFilingDetailAdapter
    private lateinit var complList: ArrayList<GSTComplianceRecord>
    private lateinit var complList1: ArrayList<GSTComplianceRecord>
    private lateinit var complList3: ArrayList<GSTComplianceRecord>
    private lateinit var singlelList: ArrayList<GSTSingleRecord>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getSerializable(ARG_PARAM2) as GstResponse
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.gsttracker_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BasicViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        complList = ArrayList()
        singlelList = ArrayList()
        complList1 = ArrayList()
        complList3 = ArrayList()

        binding.infoPar.setOnClickListener {
            showCustomViewDialog()
        }

        binding.detailsIcon.setOnClickListener {
            showCustomViewDialogDetails(BottomSheet(LayoutMode.WRAP_CONTENT))
        }

      //  data = ModelPreferences(activity!!).getObject(Const.SEARCH_DATA, GstResponse::class.java)!!
        Log.d("gstracker",data.toString())

        setData(data)

    }

    private fun showCustomViewDialog(dialogBehavior: DialogBehavior = ModalDialog) {
        MaterialDialog(activity!!, dialogBehavior).show {
            title(R.string.info)
            customView(R.layout.custom_view, scrollable = true, horizontalPadding = true)
            positiveButton(R.string.ok) { dialog ->
                dialog.cancel()
            }
        }
    }

    private fun showCustomViewDialogDetails(dialogBehavior: DialogBehavior = ModalDialog) {
        val dialog = MaterialDialog(activity!!, dialogBehavior).show {
            title(R.string.gst_filing_details)
            customView(R.layout.custom_view_2, scrollable = true, horizontalPadding = true)
        }

        val customView = dialog.getCustomView()
        val rvDetail: RecyclerView = customView.findViewById(R.id.rvGstDetail)

        rvDetail.setHasFixedSize(true)
        adapter1 = GstFilingDetailAdapter(singlelList)
        rvDetail.rvGstDetail.adapter = adapter1
        rvDetail.rvGstDetail.layoutManager = LinearLayoutManager(activity)

    }

    private fun setData(data: GstResponse) {
        binding.tvLast.text =
            data.gSTInformationAndCompliance?.gSTRegistrationDetails?.lastUpdatedDateTime
        binding.tvConstitu.text =
            data.gSTInformationAndCompliance?.gSTRegistrationDetails?.constitution

        binding.rvGstFiling.setHasFixedSize(true)
        adapter = GstFilingAdapter(combineDataList())
        binding.rvGstFiling.adapter = adapter
        binding.rvGstFiling.layoutManager = LinearLayoutManager(activity)
    }

    private fun getGstFilingList() {
        for (item in data.gSTInformationAndCompliance!!.gSTRegistrationDetails!!.gSTComplianceLastest6Months!!.gSTComplianceRecord!!)
            complList.add(item)
    }

    private fun getGstTyepWiseList() {
        for (item in complList) {
            if (item.returnType == "GSTR1") {
                complList1.add(item)
            } else {
                complList3.add(item)
            }
        }
    }

    private fun combineDataList(): ArrayList<GSTSingleRecord> {
        getGstFilingList()
        getGstTyepWiseList()
        for (item in complList1) {
            for (item1 in complList3) {
                if (item.taxPeriod == item1.taxPeriod) {
                    singlelList.add(
                        GSTSingleRecord(
                            item1.taxPeriod,
                            item.filingStatus,
                            item1.filingStatus,
                            item.filingDate, item.dueDateForTheMonth,
                            item1.filingDate, item1.dueDateForTheMonth
                        )
                    )
                }
            }
        }

        return singlelList
    }

    companion object {
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: GstResponse) =
            GstTrackerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM2, param1)
                }
            }
    }
}
