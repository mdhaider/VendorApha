package com.instafinancials.vendoralpha.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter
    private lateinit var searchHistoryList: ArrayList<HistoryDataForDb>
    private lateinit var binding: FragmentHistoryBinding
    private var db: AppDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_history_home_only)
        }

        val itemOnClick: (Int) -> Unit = { position ->
            goToHome(searchHistoryList[position].gstTinNo)
        }

        historyViewModel =
            ViewModelProviders.of(this).get(HistoryViewModel::class.java)

        searchHistoryList = ArrayList()

        db = AppDatabase.getAppDataBase(context = activity!!)
        Observable.fromCallable {
            db?.historyDataDao()?.getHistory()
        }.doOnNext { list ->

            for (item in list!!) {
                searchHistoryList.add(item)
            }

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()


        binding.rvHistoryList.setHasFixedSize(true)
        searchHistoryList.asReversed()
        adapter = HistoryAdapter(
            searchHistoryList, itemClickListener = itemOnClick
        )
        binding.rvHistoryList.adapter = adapter
        binding.rvHistoryList.layoutManager = LinearLayoutManager(activity)

    }

    private fun goToHome(gstNo: String) {
        val bundle = Bundle().apply {
            putString(Const.GST_NUMBER, gstNo)
            putBoolean(Const.IS_COMING_FROM_HISTORY, true)
        }

        findNavController().navigate(R.id.action_history_home, bundle)
    }

}