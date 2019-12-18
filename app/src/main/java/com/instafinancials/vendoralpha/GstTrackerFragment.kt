package com.instafinancials.vendoralpha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.customview.customView
import com.instafinancials.vendoralpha.databinding.GsttrackerFragmentBinding


class GstTrackerFragment : Fragment() {

    private lateinit var viewModel: BasicViewModel
    private lateinit var binding: GsttrackerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gsttracker_fragment, container, false)
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

        binding.details.setOnClickListener {
            showCustomViewDialog1()
        }
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

    private fun showCustomViewDialog1(dialogBehavior: DialogBehavior = ModalDialog) {
        MaterialDialog(activity!!, dialogBehavior).show {
            title(R.string.filing_details)
            customView(R.layout.custom_view_2, scrollable = true, horizontalPadding = true)
            positiveButton(R.string.ok) { dialog ->
                dialog.cancel()
            }
        }
    }
}
