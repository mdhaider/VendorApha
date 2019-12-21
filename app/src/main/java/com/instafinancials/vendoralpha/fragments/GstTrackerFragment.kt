package com.instafinancials.vendoralpha.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apis.GstResponse
import com.instafinancials.vendoralpha.databinding.GsttrackerFragmentBinding
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.ModelPreferences
import com.instafinancials.vendoralpha.viewmodels.BasicViewModel
import timber.log.Timber


class GstTrackerFragment : Fragment() {

    private lateinit var viewModel: BasicViewModel
    private lateinit var binding: GsttrackerFragmentBinding
    private lateinit var data: GstResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.gsttracker_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BasicViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.infoPar.setOnClickListener {
            showCustomViewDialog()
        }

        binding.detailsIcon.setOnClickListener {
            showCustomViewDialogDetails(BottomSheet(LayoutMode.WRAP_CONTENT))
        }

        data= ModelPreferences(activity!!).getObject(Const.SEARCH_DATA, GstResponse::class.java)!!
        Timber.d(data.toString())

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
        MaterialDialog(activity!!, dialogBehavior).show {
            title(R.string.gst_filing_details)
            customView(R.layout.custom_view_2, scrollable = true, horizontalPadding = true)
        }
    }

    private fun setData(data:GstResponse){
        binding.tvLast.text= data.gSTInformationAndCompliance?.gSTRegistrationDetails?.lastUpdatedDateTime
        binding.tvLocName.text= data.gSTInformationAndCompliance?.gSTRegistrationDetails?.registeredState


    }

}
