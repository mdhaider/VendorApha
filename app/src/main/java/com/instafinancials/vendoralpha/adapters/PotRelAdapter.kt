package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.models.RelatedParty
import com.instafinancials.vendoralpha.viewholders.PotRelViewHolder

class PotRelAdapter(private val list: ArrayList<RelatedParty>) :
    RecyclerView.Adapter<PotRelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PotRelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PotRelViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: PotRelViewHolder, position: Int) {
        val relatedParty: RelatedParty = list[position]
        holder.bind(relatedParty)
    }

    override fun getItemCount(): Int = list.size
}

