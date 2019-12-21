package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.apis.GSTSingleRecord
import com.instafinancials.vendoralpha.viewholders.GstFilingDetailViewHolder

class GstFilingDetailAdapter(private val list: ArrayList<GSTSingleRecord>) :
    RecyclerView.Adapter<GstFilingDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GstFilingDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GstFilingDetailViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: GstFilingDetailViewHolder, position: Int) {
        val gstComplianceRecord: GSTSingleRecord = list[position]
        holder.bind(gstComplianceRecord)
    }

    override fun getItemCount(): Int = list.size
}

