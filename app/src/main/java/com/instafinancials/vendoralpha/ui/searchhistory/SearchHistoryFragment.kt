package com.instafinancials.vendoralpha.ui.searchhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.FragmentSearchHistoryBinding


class SearchHistoryFragment : Fragment() {

    private lateinit var searchHistoryViewModel: SearchHistoryViewModel
    private lateinit var adapter: SearchHistoryAdapter
    private lateinit var searchHistoryList: ArrayList<SearchHistoryData>
    private lateinit var binding: FragmentSearchHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_history, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchHistoryViewModel =
            ViewModelProviders.of(this).get(SearchHistoryViewModel::class.java)

        searchHistoryList = ArrayList()

        searchHistoryList.add(0, SearchHistoryData("DHRRIFN9404000595", 1576298763437))
        searchHistoryList.add(0, SearchHistoryData("8UDJFHHDHDHDHHDB3", 1570290860437))
        searchHistoryList.add(0, SearchHistoryData("908IFN9404000595", 1570298763437))
        searchHistoryList.add(0, SearchHistoryData("78DJFHHDHDHDHHDB3", 1520290860437))

        binding.rvSearchHistory.setHasFixedSize(true)
        adapter = SearchHistoryAdapter(searchHistoryList)
        binding.rvSearchHistory.adapter = adapter
        binding.rvSearchHistory.layoutManager = LinearLayoutManager(activity)
    }
}