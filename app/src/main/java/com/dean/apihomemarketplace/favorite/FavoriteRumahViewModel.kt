package com.dean.apihomemarketplace.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dean.apihomemarketplace.utils.ApiService

class FavoriteRumahViewModel(val apiService: ApiService): ViewModel(){

     fun getFav() : LiveData<PagedList<FavModel>> {
         val pagedListConfig = PagedList.Config.Builder()
             .setEnablePlaceholders(false)
             .setPageSize(20).build()

         return LivePagedListBuilder(apiService.getFavorite(), pagedListConfig).run { build() }

     }

    @Suppress("UNCHECKED_CAST")
    // T : is Called when the data is changed.
    class Factory(private val application: Application, private val apiService: ApiService):
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoriteRumahViewModel(apiService) as T
        }
    }

}