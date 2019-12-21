package com.instafinancials.vendoralpha.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apis.GSTSingleRecord

class GstFilingViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_gstfiling, parent, false)) {
    private var mMonth: TextView? = null
    private var mGst1: ImageView? = null
    private var mGst3: ImageView? = null


    init {
        mMonth = itemView.findViewById(R.id.gstMonth)
        mGst1 = itemView.findViewById(R.id.gst1Status)
        mGst3 = itemView.findViewById(R.id.gst3Status)

    }

    fun bind(gstComplianceRecord: GSTSingleRecord) {

        mMonth?.text = gstComplianceRecord.taxPeriod

        if (gstComplianceRecord.gst1FilingStatus == "Filed") {
            mGst1!!.setImageResource(R.drawable.ic_checked)
        } else {
            mGst1!!.setImageResource(R.drawable.ic_group_8)
        }

        if (gstComplianceRecord.gst3FilingStatus == "Filed") {
            mGst3!!.setImageResource(R.drawable.ic_checked)
        } else {
            mGst3!!.setImageResource(R.drawable.ic_group_8)
        }
    }
}
