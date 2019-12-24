package com.instafinancials.vendoralpha.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.adapters.HistoryAdapter
import com.instafinancials.vendoralpha.databinding.FragmentHistoryBinding
import com.instafinancials.vendoralpha.db.AppDatabase
import com.instafinancials.vendoralpha.db.HistoryDataForDb
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.viewmodels.HistoryViewModel
import java.util.Collections.reverse


class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter
    private lateinit var searchHistoryList: List<HistoryDataForDb>
    private lateinit var binding: FragmentHistoryBinding
    private var db: AppDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
               R.layout.fragment_history,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        db = AppDatabase.getAppDataBase(context = activity!!)
        searchHistoryList = ArrayList()
        binding.btnBack.setOnClickListener(_onItemClick)

        retrieveHistoryList()
    }

    private val _onItemClick = View.OnClickListener {
        when (it.id) {
            R.id.btnBack -> {
                findNavController().navigate(com.instafinancials.vendoralpha.R.id.action_history_home_only)
            }
        }
    }

    private fun retrieveHistoryList() {
        db?.historyDataDao()?.getHistory()?.observe(this,
            Observer<List<HistoryDataForDb>> { history ->
                searchHistoryList = history
                setUpAdapter(searchHistoryList)
            })
    }

    private fun setUpAdapter(list: List<HistoryDataForDb>) {
        val itemOnClick: (Int) -> Unit = { position ->
            goToHome(list[position].gstTinNo)
        }

        reverse(list)

        binding.rvHistoryList.setHasFixedSize(true)
        adapter = HistoryAdapter(
            list, itemClickListener = itemOnClick
        )
        binding.rvHistoryList.adapter = adapter
        binding.rvHistoryList.layoutManager = LinearLayoutManager(activity)
    }

    private fun goToHome(gstNo: String) {
        val bundle = Bundle().apply {
            putString(Const.GST_NUMBER, gstNo)
            putBoolean(Const.IS_COMING_FROM_HISTORY, true)
        }

        findNavController().navigate(
            R.id.action_history_home,
            bundle
        )
    }
}