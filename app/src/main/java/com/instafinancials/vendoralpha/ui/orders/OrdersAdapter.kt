package com.instafinancials.vendoralpha.ui.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OrdersAdapter(private val list: ArrayList<OrdersData>)
    : RecyclerView.Adapter<OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OrdersViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val ordersData: OrdersData = list[position]
        holder.bind(ordersData)
    }

    override fun getItemCount(): Int = list.size

}
