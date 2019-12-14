package com.instafinancials.vendoralpha.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.TimeAgo

class BookmarkViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_bookmark, parent, false)) {
    private var mCinNumber: TextView? = null
    private var mDate: TextView? = null


    init {
        mCinNumber = itemView.findViewById(R.id.tvCin)
        mDate = itemView.findViewById(R.id.tvDate)

    }

    fun bind(historyData: BookmarkData) {
        mCinNumber?.text = historyData.cin
        mDate?.text = TimeAgo.convertLongToTime(historyData.date!!)

    }
}
