package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.db.HistoryDataForDb
import com.instafinancials.vendoralpha.viewholders.HistoryViewHolder
import com.instafinancials.vendoralpha.viewholders.NoHistoryViewHolder


class HistoryAdapter(
    private var list: List<HistoryDataForDb>,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_DATA = 1
        const val NO_DATA = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var holder: RecyclerView.ViewHolder ?= null
        when (viewType) {
            VIEW_DATA -> {
                holder = HistoryViewHolder(inflater, parent)
            }

            NO_DATA -> {
                holder = NoHistoryViewHolder(inflater, parent)
            }

        }
        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoryViewHolder) {
            val historyDataForDb = list[position]
            holder.bind(historyDataForDb, itemClickListener)
        }

    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) {
            1
        } else {
            list.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty()) {
            NO_DATA
        } else {
            VIEW_DATA
        }
    }
}
