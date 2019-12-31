package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.instafinancials.vendoralpha.shared.TaxPayerEnum
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
    private var retType3: String? = null
    private var noOfCol = 0

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
        val gsttxt1: TextView = customView.findViewById(R.id.cstvgst1)
        val gsttxt3: TextView = customView.findViewById(R.id.cstvgst3)

        if (noOfCol == 2) {
            gsttxt1.text = "GSTR1"
            gsttxt3.text = "GSTR3B"
        } else {
            gsttxt1.text = retType3
            gsttxt3.visibility = View.INVISIBLE
        }

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
        val taxType = data.gSTInformationAndCompliance?.gSTRegistrationDetails?.taxpayerType

        if (!TextUtils.isEmpty(taxType)) {
            for (item in TaxPayerEnum.values()) {
                if (taxType!!.contains(item.taxType, true)) {
                    retType3 = item.retType
                    noOfCol = item.noOfCol
                    break
                }
            }
        } else {
            return
        }

        if (noOfCol == 2) {
            binding.tvGst1.text = "GSTR1"
            binding.tvGst3.text = "GSTR3B"
            adapter = GstFilingAdapter(combineDataList())
        } else {
            binding.tvGst1.text = retType3
            adapter = GstFilingAdapter(combineDataList1(retType3!!))
            binding.tvGst3.visibility = View.INVISIBLE
        }

        binding.rvGstFiling.setHasFixedSize(true)
        binding.rvGstFiling.adapter = adapter
        binding.rvGstFiling.layoutManager = LinearLayoutManager(activity)

        if (singlelList.isEmpty()) {
            binding.firstRow.visibility = View.GONE
            binding.detailsIcon.visibility = View.INVISIBLE
            binding.rvGstFiling.visibility = View.GONE
            binding.tvNA.visibility = View.VISIBLE
        }
    }

    private fun getGstFilingList() {
        for (item in data.gSTInformationAndCompliance!!.gSTRegistrationDetails!!.gSTComplianceLastest6Months!!.gSTComplianceRecord!!)
            complList.add(item)
    }

    private fun getGstTyepWiseList() {
        for (item in complList) {
            if (item.returnType == "GSTR1") {
                complList1.add(item)
            } else if (item.returnType == "GSTR3B") {
                complList3.add(item)
            }
        }
    }

    private fun getGstTyepWiseList2(retType1: String) {
        for (item in complList) {
            if (item.returnType == retType1) {
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
                            2,
                            retType3,
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


    private fun combineDataList1(retType: String): ArrayList<GSTSingleRecord> {
        getGstFilingList()
        getGstTyepWiseList2(retType)
        for (item in complList1) {
            singlelList.add(
                GSTSingleRecord(
                    1,
                    retType3,
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
