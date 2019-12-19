package com.instafinancials.vendoralpha.apicall.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.apicall.network.apinew.GstViewModel
import com.instafinancials.vendoralpha.apicall.network.apinew.GstViewModelFactory
import kotlinx.android.synthetic.main.activity_main_1.*

class MainActivity1 : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var gstViewModel: GstViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_1)
        val viewModelFactory = NewsViewModelFactory()
        val gstViewModelFactory = GstViewModelFactory()


     /*   newsViewModel =  ViewModelProviders.of(this@MainActivity1, viewModelFactory)
                .get(NewsViewModel::class.java)
        newsViewModel.getLatestNews()
        newsViewModel.newsLiveData.observe(this, Observer {
            hello.text= it.toString()

        })*/

        gstViewModel =  ViewModelProviders.of(this@MainActivity1, gstViewModelFactory)
            .get(GstViewModel::class.java)
        gstViewModel.getGst()
        gstViewModel.gstLiveData.observe(this, Observer {
            hello.text= it.toString()

        })

        }
    }