package com.instafinancials.vendoralpha.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.db.BookmarkDataForDb
import java.text.SimpleDateFormat
import java.util.*

class BookmarkViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_bookmark, parent, false)) {
    private var mCinNumber: TextView? = null
    private var mComName: TextView? = null
    private var mDate: TextView? = null

    init {
        mCinNumber = itemView.findViewById(R.id.tvGst)
        mComName = itemView.findViewById(R.id.tvComName)
        mDate = itemView.findViewById(R.id.tvDate)
    }

    fun bind(bookmarkDataForDb: BookmarkDataForDb, itemClickListener:(Int)->Unit) {
        mCinNumber?.text = bookmarkDataForDb.gstTinNo
        mComName?.text = (bookmarkDataForDb.comName)
        mDate?.text = getStringDate(bookmarkDataForDb.dueDay)

        itemView.setOnClickListener { itemClickListener(adapterPosition) }
    }

    private fun getStringDate(date: Date): String {
        val formatter = SimpleDateFormat("dd-MMM-yy hh.mm aa", Locale.ENGLISH)
        return formatter.format(date)
    }
}
