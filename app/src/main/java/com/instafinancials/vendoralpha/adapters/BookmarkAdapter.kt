package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.db.BookmarkDataForDb
import com.instafinancials.vendoralpha.viewholders.BookmarkViewHolder

class BookmarkAdapter(private val list: List<BookmarkDataForDb>, private val itemClickListener: (Int) -> Unit)
    : RecyclerView.Adapter<BookmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookmarkViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val historyDataForDb: BookmarkDataForDb = list[position]
        holder.bind(historyDataForDb, itemClickListener)
    }

    override fun getItemCount(): Int = list.size

}
