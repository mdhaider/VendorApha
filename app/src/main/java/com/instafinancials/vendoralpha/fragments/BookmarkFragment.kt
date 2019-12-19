package com.instafinancials.vendoralpha.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.adapters.BookmarkAdapter
import com.instafinancials.vendoralpha.databinding.FragmentBookmarkBinding
import com.instafinancials.vendoralpha.models.BookmarkData
import com.instafinancials.vendoralpha.viewmodels.BookmarkViewModel


class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter
    private lateinit var searchHistoryList: ArrayList<BookmarkData>
    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkViewModel =
            ViewModelProviders.of(this).get(BookmarkViewModel::class.java)

        searchHistoryList = ArrayList()

        searchHistoryList.add(0,
            BookmarkData(
                "DHRRIFN9404000595",
                1576298763437
            )
        )
        searchHistoryList.add(0,
            BookmarkData(
                "8UDJFHHDHDHDHHDB3",
                1570290860437
            )
        )
        searchHistoryList.add(0,
            BookmarkData(
                "908IFN9404000595",
                1570298763437
            )
        )
        searchHistoryList.add(0,
            BookmarkData(
                "78DJFHHDHDHDHHDB3",
                1520290860437
            )
        )

        binding.rvBookmarkList.setHasFixedSize(true)
        adapter = BookmarkAdapter(
            searchHistoryList
        )
        binding.rvBookmarkList.adapter = adapter
        binding.rvBookmarkList.layoutManager = LinearLayoutManager(activity)
    }
}