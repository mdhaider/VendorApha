package com.instafinancials.vendoralpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.apis.Director
import com.instafinancials.vendoralpha.apis.Signatory
import com.instafinancials.vendoralpha.viewholders.SignatoryViewHolder

class SignatoryAdapter(private val list: ArrayList<Signatory>) :
    RecyclerView.Adapter<SignatoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignatoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SignatoryViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: SignatoryViewHolder, position: Int) {
        val director: Signatory = list[position]
        holder.bind(director)
    }

    override fun getItemCount(): Int = list.size
}

