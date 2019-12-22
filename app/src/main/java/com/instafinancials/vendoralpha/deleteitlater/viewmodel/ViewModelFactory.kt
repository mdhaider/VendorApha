package com.instafinancials.vendoralpha.deleteitlater.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.instafinancials.vendoralpha.deleteitlater.model.MuseumDataSource

class ViewModelFactory(private val repository: MuseumDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MuseumViewModel(repository) as T
    }
}