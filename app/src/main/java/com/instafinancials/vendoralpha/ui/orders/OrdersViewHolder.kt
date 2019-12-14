package com.instafinancials.vendoralpha.ui.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.TimeAgo

class OrdersViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_orders, parent, false)) {
    private var mCompanyName: TextView? = null
    private var mOrderedReport: TextView? = null
    private var mOrderedDate: TextView? = null
    private var mOrderStatus: TextView? = null
    private var mDeliveredDate: TextView? = null


    init {
        mCompanyName = itemView.findViewById(R.id.tvCompanyName)
        mOrderedReport = itemView.findViewById(R.id.tvOrderedReport)
        mOrderedDate = itemView.findViewById(R.id.tvOrderedDate)
        mOrderStatus = itemView.findViewById(R.id.tvOrderStatus)
        mDeliveredDate = itemView.findViewById(R.id.tvDeliveredDate)

    }

    fun bind(ordersData: OrdersData) {
        mCompanyName?.text = ordersData.companyName
        mOrderedReport?.text = ordersData.orderedReport
        mOrderedDate?.text = TimeAgo.getTimeAgo(ordersData.orderedDate!!)
        mOrderStatus?.text = ordersData.orderedStatus
        mDeliveredDate?.text = TimeAgo.getTimeAgo(ordersData.deliveredDate!!)

    }
}
