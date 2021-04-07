package com.dean.apihomemarketplace.favorite

import android.app.Application
import com.dean.apihomemarketplace.room.FavDatabase
import com.dean.apihomemarketplace.utils.ApiService


object Injection {
    fun provideRepository(application: Application): ApiService {

        val database = FavDatabase.getInstance(application)
        val appExecutors = AppExecutors()
        return ApiService.getInstance(database.favDao(), appExecutors)
    }
}