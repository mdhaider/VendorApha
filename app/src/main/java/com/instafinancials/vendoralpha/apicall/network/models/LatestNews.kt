package com.instafinancials.vendoralpha.apicall.network.models

data class LatestNews(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)