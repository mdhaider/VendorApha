package com.instafinancials.vendoralpha.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookmarkAdapter(private val list: ArrayList<BookmarkData>)
    : RecyclerView.Adapter<BookmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookmarkViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val historyData: BookmarkData = list[position]
        holder.bind(historyData)
    }

    override fun getItemCount(): Int = list.size

}
