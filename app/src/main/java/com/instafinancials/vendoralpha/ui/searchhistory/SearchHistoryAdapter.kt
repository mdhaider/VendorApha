package com.instafinancials.vendoralpha.ui.searchhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SearchHistoryAdapter(private val list: ArrayList<SearchHistoryData>)
    : RecyclerView.Adapter<SearchHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchHistoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val historyData: SearchHistoryData = list[position]
        holder.bind(historyData)
    }

    override fun getItemCount(): Int = list.size

}
