package com.instafinancials.vendoralpha.apicall.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.instafinancials.vendoralpha.apicall.network.NewsApiService
import com.instafinancials.vendoralpha.apicall.network.models.Article
import com.instafinancials.vendoralpha.apicall.repositories.NewsRepo
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsViewModel : ViewModel(){
    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val newsRepository : NewsRepo = NewsRepo(NewsApiService.newsApi)
     val newsLiveData = MutableLiveData<MutableList<Article>>()
    fun getLatestNews() {
        scope.launch {
            val latestNews = newsRepository.getLatestNews()
            newsLiveData.postValue(latestNews)

        }
    }
    fun cancelRequests() = coroutineContext.cancel()
}