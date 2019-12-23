package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.FragmentOrdersBinding
import com.instafinancials.vendoralpha.models.OrdersData
import com.instafinancials.vendoralpha.adapters.OrdersAdapter
import com.instafinancials.vendoralpha.viewmodels.OrdersViewModel


class OrderFragment : Fragment() {

    private lateinit var ordersViewModel: OrdersViewModel
    private lateinit var adapter: OrdersAdapter
    private lateinit var ordersList: ArrayList<OrdersData>
    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_orders, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersViewModel =
            ViewModelProviders.of(this).get(OrdersViewModel::class.java)

        ordersList = ArrayList()

        ordersList.add(0,
            OrdersData(
                "CBL Data",
                "Report1",
                1576298763437,
                "Processing",
                1576298763437
            )
        )
        ordersList.add(0,
            OrdersData(
                "CBL Data1",
                "Report1",
                1576298763437,
                "Processing",
                1576298763437
            )
        )
        ordersList.add(0,
            OrdersData(
                "CBL Data3",
                "Report2",
                1576298763437,
                "Processing",
                1576298763437
            )
        )
        ordersList.add(0,
            OrdersData(
                "CBL Data4",
                "Report3",
                1576298763437,
                "Processing",
                1576298763437
            )
        )
        ordersList.add(0,
            OrdersData(
                "CBL Data5",
                "Report4",
                1576298763437,
                "Processing",
                1576298763437
            )
        )

        binding.rvOrders.setHasFixedSize(true)
        adapter =
            OrdersAdapter(ordersList)
        binding.rvOrders.adapter = adapter
        binding.rvOrders.layoutManager = LinearLayoutManager(activity)
    }
}