package com.instafinancials.vendoralpha.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apis.RelatedParty

class PotRelViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pot_rel_party, parent, false)) {
    private var mComName: TextView? = null
    private var mMcaStatus: TextView? = null
    private var mPaidCap: TextView? = null

    init {
        mComName = itemView.findViewById(R.id.tvComName1)
        mMcaStatus = itemView.findViewById(R.id.tvMcaStatus)
        mPaidCap = itemView.findViewById(R.id.tvPaidCap)
    }

    fun bind(relatedParty: RelatedParty) {
        mComName?.text = relatedParty.companyName
        mMcaStatus?.text = relatedParty.companyMcaStatus
        mPaidCap?.text = relatedParty.companyPaidUpCapital.toString()
    }
}
