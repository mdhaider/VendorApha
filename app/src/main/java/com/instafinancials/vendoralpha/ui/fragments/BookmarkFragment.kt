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
import com.instafinancials.vendoralpha.adapters.BookmarkAdapter
import com.instafinancials.vendoralpha.databinding.FragmentBookmarkBinding
import com.instafinancials.vendoralpha.db.AppDatabase
import com.instafinancials.vendoralpha.db.BookmarkDataForDb
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.viewmodels.BookmarkViewModel
import java.util.Collections.reverse


class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter
    private lateinit var bookmarkList: List<BookmarkDataForDb>
    private lateinit var binding: FragmentBookmarkBinding
    private var db: AppDatabase? = null

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

        bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
        db = AppDatabase.getAppDataBase(context = activity!!)
        bookmarkList = ArrayList()

        binding.btnBack.setOnClickListener(_onItemClick)

        retrieveTasks()
    }

    private val _onItemClick = View.OnClickListener {
        when (it.id) {
            R.id.btnBack -> {
                findNavController().navigate(R.id.action_book_home_only)
            }
        }
    }

    private fun retrieveTasks() {
        db?.bookmarkDataDao()?.getBookmark()?.observe(this,
            Observer<List<BookmarkDataForDb>> { bookmark ->
                bookmarkList = bookmark
                setUpAdapter(bookmarkList)
            })
    }

    private fun setUpAdapter(list: List<BookmarkDataForDb>) {
        val itemOnClick: (Int) -> Unit = { position ->
            goToHome(list[position].gstTinNo)
        }

        reverse(list)

        binding.rvBookmarkList.setHasFixedSize(true)
        adapter = BookmarkAdapter(
            list, itemClickListener = itemOnClick
        )
        binding.rvBookmarkList.adapter = adapter
        binding.rvBookmarkList.layoutManager = LinearLayoutManager(activity)
    }


    private fun goToHome(gstNo: String) {
        val bundle = Bundle().apply {
            putString(Const.GST_NUMBER, gstNo)
            putBoolean(Const.IS_COMING_FROM_BOOKMARK, true)
        }

        findNavController().navigate(R.id.action_book_home, bundle)
    }

}