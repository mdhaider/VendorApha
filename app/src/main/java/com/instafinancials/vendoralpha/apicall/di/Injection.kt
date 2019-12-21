package com.instafinancials.vendoralpha.apicall.di

import com.instafinancials.vendoralpha.apicall.model.MuseumDataSource
import com.instafinancials.vendoralpha.apicall.model.MuseumRepository

object Injection {

    //MuseumRepository could be a singleton
    fun providerRepository(cinNumber:String): MuseumDataSource {
        return MuseumRepository(cinNumber)
    }
}