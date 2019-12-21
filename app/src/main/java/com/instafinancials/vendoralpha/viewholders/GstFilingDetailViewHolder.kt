package com.instafinancials.vendoralpha.viewholders

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apis.GSTSingleRecord
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        mMonth?.text = gstComplianceRecord.taxPeriod
        mGst1Fildat?.text = stringToDate(gstComplianceRecord.gst1FilingDat!!)
        mGst1Duedat?.text = "("+getDifferenceDays(stringToDate1(gstComplianceRecord.gst1DueDat!!),stringToDate1(gstComplianceRecord.gst1FilingDat)).toString()+")"
        mGst3Fildat?.text = stringToDate(gstComplianceRecord.gst3FilingDat!!)
        mGst3Duedat?.text = "("+getDifferenceDays(stringToDate1(gstComplianceRecord.gst3DueDat!!),stringToDate1(gstComplianceRecord.gst3FilingDat)).toString()+")"

    }

    fun stringToDate(datesString: String): String {
        var date1: Date
        var dateTime: String? = null
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val dateFormat = SimpleDateFormat("dd/MM", Locale.ENGLISH)
        try {
            date1 = format.parse(datesString)
            dateTime = dateFormat.format(date1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateTime!!
    }

    fun stringToDate1(datesString: String): Date {
        var date1: Date= Date()
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        try {
            date1 = format.parse(datesString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date1
    }

    fun dateToString(date1: Date): String {
        var dateTime: String? = null
        val dateFormat = SimpleDateFormat("dd/MM", Locale.ENGLISH)
        try {
            dateTime = dateFormat.format(date1)
            Log.d("Current Date Time", dateTime.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateTime!!
    }


    fun getDifferenceDays(d1: Date, d2: Date): Int {
        var daysdiff = 0
        val diff = d2.time - d1.time
        val diffDays = diff / (24 * 60 * 60 * 1000) + 1
        daysdiff = diffDays.toInt()
        return daysdiff
    }
}
