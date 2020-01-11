package com.instafinancials.vendoralpha.viewholders

import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.models.GSTSingleRecord
import com.instafinancials.vendoralpha.shared.TimeUtil.getDifferenceDays
import com.instafinancials.vendoralpha.shared.TimeUtil.stringToDate
import com.instafinancials.vendoralpha.shared.TimeUtil.stringToString

class GstFilingDetailViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_gstfiling_detail, parent, false)) {
    private var mMonth: TextView? = null
    private var mGst1Fildat: TextView? = null
    private var mGst1Duedat: TextView? = null
    private var mGst3Fildat: TextView? = null
    private var mGst3Duedat: TextView? = null

    init {
        mMonth = itemView.findViewById(R.id.tvFilPeriod)
        mGst1Fildat = itemView.findViewById(R.id.tvGst1FilDate)
        mGst1Duedat = itemView.findViewById(R.id.tvGst1FilDateDiff)
        mGst3Fildat = itemView.findViewById(R.id.tvGst3FilDate)
        mGst3Duedat = itemView.findViewById(R.id.tvGst3FilDateDiff)
    }

    fun bind(gstComplianceRecord: GSTSingleRecord) {
        mMonth?.text = gstComplianceRecord.taxPeriod?.substring(0,3)+", "+gstComplianceRecord.financialYear?.substring(2,4)
        mGst1Fildat?.text = stringToString(gstComplianceRecord.gst1FilingDat!!)
        setDueData1(gstComplianceRecord)

        if (gstComplianceRecord.noOfCol == 2) {
            mGst3Fildat?.text = stringToString(gstComplianceRecord.gst3FilingDat!!)
            setDueData3(gstComplianceRecord)
        } else {
            mGst3Fildat!!.visibility = View.INVISIBLE
            mGst3Duedat!!.visibility = View.INVISIBLE
        }
    }

    private fun setDueData1(rec: GSTSingleRecord) {
        if (TextUtils.isEmpty(rec.gst1DueDat) || TextUtils.isEmpty(rec.gst1FilingDat)) {
            return
        }
        val int =
            getDifferenceDays(stringToDate(rec.gst1DueDat!!), stringToDate(rec.gst1FilingDat!!))
        if (int >= 0) {
            mGst1Duedat?.text = "(" + int.toString() + ")"
            mGst1Duedat?.setTextColor(Color.parseColor("#1fb14c"))
        } else {
            mGst1Duedat?.text = "(" + int.toString() + ")"
            mGst1Duedat?.setTextColor(Color.parseColor("#ee312b"))
        }
    }

    private fun setDueData3(rec: GSTSingleRecord) {
        if (TextUtils.isEmpty(rec.gst3DueDat) || TextUtils.isEmpty(rec.gst3FilingDat)) {
            return
        }
        val int =
            getDifferenceDays(stringToDate(rec.gst3DueDat!!), stringToDate(rec.gst3FilingDat!!))
        if (int >= 0) {
            mGst3Duedat?.text = "(" + int.toString() + ")"
            mGst3Duedat?.setTextColor(Color.parseColor("#1fb14c"))
        } else {
            mGst3Duedat?.text = "(" + int.toString() + ")"
            mGst3Duedat?.setTextColor(Color.parseColor("#ee312b"))
        }
    }
}
