package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.models.GSTSingleRecord
import com.instafinancials.vendoralpha.viewholders.GstFilingViewHolder

class GstFilingAdapter(private val list: ArrayList<GSTSingleRecord>)
    : RecyclerView.Adapter<GstFilingViewHolder>() {
    private val limit = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GstFilingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GstFilingViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: GstFilingViewHolder, position: Int) {
        val gstComplianceRecord: GSTSingleRecord = list[position]
        holder.bind(gstComplianceRecord)
    }

    override fun getItemCount(): Int {
        return if(list.size > limit){
            limit
        } else {
            list.size
        }
    }
}
