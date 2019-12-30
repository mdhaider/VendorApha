package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
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
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.TimeUtil
import com.instafinancials.vendoralpha.viewmodels.BasicViewModel
import kotlinx.android.synthetic.main.custom_view_2.view.*

class GstTrackerFragment : Fragment() {

    private lateinit var viewModel: BasicViewModel
    private lateinit var binding: GsttrackerFragmentBinding
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

        binding.imgRefresh.setOnClickListener {
            //  goToHome(binding.imgRefresh,data.gSTInformationAndCompliance?.gSTRegistrationDetails?.gSTIN!!)
        }

        Log.d("gstracker", data.toString())

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
        if (!TextUtils.isEmpty(data.gSTInformationAndCompliance?.gSTRegistrationDetails?.lastUpdatedDateTime)) {
            binding.tvLast.text =
                TimeUtil.stringToString1(data.gSTInformationAndCompliance?.gSTRegistrationDetails?.lastUpdatedDateTime!!)
        } else {
            binding.tvLast.text = getString(R.string.na)
        }

        if (!TextUtils.isEmpty(data.gSTInformationAndCompliance?.gSTRegistrationDetails?.constitution)) {
            binding.tvConstitu.text =
                data.gSTInformationAndCompliance?.gSTRegistrationDetails?.constitution
        } else {
            binding.tvConstitu.text = getString(R.string.na)
        }

        if (!TextUtils.isEmpty(data.gSTInformationAndCompliance?.gSTRegistrationDetails?.eligibleToCollect)) {
            if (data.gSTInformationAndCompliance?.gSTRegistrationDetails?.eligibleToCollect == "true") {
                binding.tvEligibleText.text = getString(R.string.yes_text)
                binding.tvEligibleText.setTextColor(resources.getColor(R.color.green))
            } else {
                binding.tvEligibleText.text = getString(R.string.no_text)
                binding.tvEligibleText.setTextColor(resources.getColor(R.color.red))
            }

        } else {
            binding.tvEligibleText.text = getString(R.string.na)
        }

        if (!TextUtils.isEmpty(data.gSTInformationAndCompliance?.gSTRegistrationDetails?.filingStatus)) {
            binding.tvFilStatusText.text =
                data.gSTInformationAndCompliance?.gSTRegistrationDetails?.filingStatus
        } else {
            binding.tvFilStatusText.text = getString(R.string.na)
        }

        setGstFilingDetails(data)
    }

    private fun setGstFilingDetails(data: GstResponse) {
        val taxPayerTypeList = listOf("Regular", "SEZ Unit", "SEZ Developer")
        var type = false

        for (item in taxPayerTypeList) {
            if((data.gSTInformationAndCompliance?.gSTRegistrationDetails?.taxpayerType) == item){
                type=true
                break
            }
        }

        if (type) {
            adapter = GstFilingAdapter(combineDataList())
            binding.tvGst1.text = "GST1"
            binding.tvGst3.text = "GST 3B"
        } else {
            adapter = GstFilingAdapter(combineDataList1())
        }
        binding.rvGstFiling.setHasFixedSize(true)
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

    private fun getGstTyepWiseList1() {
        val taxpayertype= data.gSTInformationAndCompliance?.gSTRegistrationDetails?.taxpayerType!!
        var returntype=""
        if(taxpayertype.contains("Composition", true)){
            returntype ="GSTR4"
        } else if(taxpayertype.contains("Distributor", true)){
            returntype ="GSTR6"
        } else if(taxpayertype.contains("collector", true)){
            returntype ="GSTR8"
        } else if(taxpayertype.contains("deductor", true)){
            returntype ="GSTR7"
        } else if(taxpayertype.contains("Nonresident ", true)){
            returntype ="GSTR5"
        } else if(taxpayertype.contains("Diplomatic ", true)){
            returntype ="GSTR11"
        } else if(taxpayertype.contains("Other", true)){
            returntype ="GSTR5A"
        } else if(taxpayertype.contains("Casual ", true)){
            returntype ="GSTR3b"
        }

        binding.tvGst1.text =returntype
        binding.tvGst3.visibility = View.GONE

        for (item in complList) {
            if (item.returnType == returntype) {
                complList1.add(item)
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
                            "",
                            item1.taxPeriod,
                            item1.financialYear,
                            item.filingStatus,
                            item.filingDate,
                            item.dueDateForTheMonth,
                            item1.filingStatus,
                            item1.filingDate, item1.dueDateForTheMonth
                        )
                    )
                }
            }
        }

        return singlelList
    }


    private fun combineDataList1(): ArrayList<GSTSingleRecord> {
        getGstFilingList()
        getGstTyepWiseList1()
        for (item in complList1) {
            singlelList.add(
                GSTSingleRecord(
                    "",
                    item.taxPeriod,
                    item.financialYear,
                    item.filingStatus,
                    item.filingDate,
                    item.dueDateForTheMonth,
                    "",
                    "", ""
                )
            )
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

    private fun goToHome(img: View, gstNo: String) {
        val bundle = Bundle().apply {
            putString(Const.GST_NUMBER, gstNo)
        }

        Navigation.findNavController(img).navigate(R.id.action_gsttracker_home, bundle)
    }
}
