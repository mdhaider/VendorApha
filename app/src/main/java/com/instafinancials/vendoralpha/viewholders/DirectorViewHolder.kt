package com.instafinancials.vendoralpha.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apis.Director

class DirectorViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_director, parent, false)) {
    private var mDirname: TextView? = null
    private var mDirApp: TextView? = null

    init {
        mDirname = itemView.findViewById(R.id.tvDirname)
        mDirApp = itemView.findViewById(R.id.tvPosition)
    }

    fun bind(director: Director) {
        mDirname?.text = director.directorName
        mDirApp?.text = director.directorDesignation+" "+"("+director.directorDateOfAppnt+")"

    }
}
