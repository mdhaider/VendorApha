package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.apis.Director
import com.instafinancials.vendoralpha.viewholders.DirectorViewHolder

class DirectorAdapter(private val list: ArrayList<Director>) :
    RecyclerView.Adapter<DirectorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DirectorViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: DirectorViewHolder, position: Int) {
        val director: Director = list[position]
        holder.bind(director)
    }

    override fun getItemCount(): Int = list.size
}

