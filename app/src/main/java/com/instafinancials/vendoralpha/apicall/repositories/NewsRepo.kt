package com.instafinancials.vendoralpha.apicall.repositories

import com.instafinancials.vendoralpha.apicall.network.NewsApiInterface
import com.instafinancials.vendoralpha.apicall.network.models.Article

class NewsRepo(private val apiInterface: NewsApiInterface) : BaseRepository() {
    suspend fun getLatestNews() :  MutableList<Article>?{
        return safeApiCall(
                call = {apiInterface.fetchLatestNewsAsync("Nigeria", "publishedAt").await()},
                error = "Error fetching news"
        )?.articles?.toMutableList()
    }
}