package com.instafinancials.vendoralpha.ui.fragments

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
import com.instafinancials.vendoralpha.adapters.BookmarkAdapter
import com.instafinancials.vendoralpha.databinding.FragmentBookmarkBinding
import com.instafinancials.vendoralpha.db.AppDatabase
import com.instafinancials.vendoralpha.db.BookmarkDataForDb
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.viewmodels.BookmarkViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter
    private lateinit var bookmarkList: ArrayList<BookmarkDataForDb>
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

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_book_home_only)
        }

        val itemOnClick: (Int) -> Unit = { position ->
            goToHome(bookmarkList[position].gstTinNo)
        }

        bookmarkViewModel =
            ViewModelProviders.of(this).get(BookmarkViewModel::class.java)

        bookmarkList = ArrayList()

        db = AppDatabase.getAppDataBase(context = activity!!)
        Observable.fromCallable {
            db?.bookmarkDataDao()?.getBookmark()
        }.doOnNext { list ->

            for(item in list!!){
                bookmarkList.add(item)
            }

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()


        binding.rvBookmarkList.setHasFixedSize(true)
        bookmarkList.asReversed()
        adapter = BookmarkAdapter(
            bookmarkList,itemClickListener = itemOnClick)
        binding.rvBookmarkList.adapter = adapter
        binding.rvBookmarkList.layoutManager = LinearLayoutManager(activity)
    }

    private fun goToHome(gstNo:String) {
        val bundle = Bundle().apply {
            putString(Const.GST_NUMBER, gstNo)
            putBoolean(Const.IS_COMING_FROM_BOOKMARK, true)
        }

        findNavController().navigate(R.id.action_book_home, bundle)
    }

}