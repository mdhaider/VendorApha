package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.db.HistoryDataForDb
import com.instafinancials.vendoralpha.viewholders.HistoryViewHolder

class HistoryAdapter(private val list: ArrayList<HistoryDataForDb>, private val itemClickListener: (Int) -> Unit)
    : RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HistoryViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyDataForDb = list[position]
        holder.bind(historyDataForDb, itemClickListener)
    }

    override fun getItemCount(): Int = list.size

}
