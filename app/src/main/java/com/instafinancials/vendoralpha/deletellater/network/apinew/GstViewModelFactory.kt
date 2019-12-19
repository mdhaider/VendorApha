package com.instafinancials.vendoralpha.deletellater.network.apinew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class GstViewModelFactory : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GstViewModel() as T
    }
}