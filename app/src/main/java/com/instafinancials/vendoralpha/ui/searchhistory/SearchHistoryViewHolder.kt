package com.instafinancials.vendoralpha.ui.searchhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.TimeAgo

class SearchHistoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_search_history, parent, false)) {
    private var mCinNumber: TextView? = null
    private var mDate: TextView? = null


    init {
        mCinNumber = itemView.findViewById(R.id.tvCin)
        mDate = itemView.findViewById(R.id.tvDate)

    }

    fun bind(historyData: SearchHistoryData) {
        mCinNumber?.text = historyData.cin
        mDate?.text = TimeAgo.getTimeAgo(historyData.date!!)

    }
}
