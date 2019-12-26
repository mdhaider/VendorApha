package com.instafinancials.vendoralpha.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R

class NoHistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.not_data, parent, false)) {
    private var mNoData: TextView? = null


    init {
        mNoData = itemView.findViewById(R.id.tvNoDataMsg)
    }

    fun bind() {
        mNoData?.text = "No History Data bab"

    }
}
