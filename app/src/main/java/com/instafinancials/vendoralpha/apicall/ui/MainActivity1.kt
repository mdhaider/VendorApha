package com.instafinancials.vendoralpha.apicall.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.instafinancials.vendoralpha.R
import kotlinx.android.synthetic.main.activity_main_1.*

class MainActivity1 : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_1)
        val viewModelFactory = NewsViewModelFactory()
        newsViewModel =  ViewModelProviders.of(this@MainActivity1, viewModelFactory)
                .get(NewsViewModel::class.java)
        newsViewModel.getLatestNews()
        newsViewModel.newsLiveData.observe(this, Observer {
            hello.text= it.toString()

        })

        }
    }