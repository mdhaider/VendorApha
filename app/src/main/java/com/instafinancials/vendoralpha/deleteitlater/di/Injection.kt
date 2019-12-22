package com.instafinancials.vendoralpha.deleteitlater.di

import com.instafinancials.vendoralpha.deleteitlater.model.MuseumDataSource
import com.instafinancials.vendoralpha.deleteitlater.model.MuseumRepository

object Injection {

    //MuseumRepository could be a singleton
    fun providerRepository(cinNumber:String): MuseumDataSource {
        return MuseumRepository(cinNumber)
    }
}