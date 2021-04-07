package com.dean.apihomemarketplace.favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.activity.DetailViewModel
import com.dean.apihomemarketplace.utils.ApiService



class ViewModelFactory private constructor(private val mApiService: ApiService) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FavoriteRumahViewModel::class.java)) {

            return FavoriteRumahViewModel(mApiService) as T

        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mApiService) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                        INSTANCE = INSTANCE
                    }
                }
            }
            return INSTANCE
        }
    }

}