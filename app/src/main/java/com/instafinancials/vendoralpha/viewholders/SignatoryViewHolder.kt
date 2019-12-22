package com.instafinancials.vendoralpha.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.models.Signatory

class SignatoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_signatory, parent, false)) {
    private var mSignname: TextView? = null
    private var mDirApp: TextView? = null

    init {
        mSignname = itemView.findViewById(R.id.tvSignName)
        mDirApp = itemView.findViewById(R.id.tvSignPos)
    }

    fun bind(signatory: Signatory) {
        mSignname?.text = signatory.signatoryName
        mDirApp?.text = signatory.signatoryDesignation+" "+"("+signatory.signatoryDateOfAppnt+")"

    }
}
