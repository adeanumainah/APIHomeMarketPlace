package com.dean.apihomemarketplace.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dean.apihomemarketplace.favorite.FavModel
import com.dean.apihomemarketplace.utils.ApiService


class DetailViewModel( val apiService: ApiService):ViewModel(){
    fun saveFav(favModel: FavModel): LiveData<FavModel> {
        return apiService.saveFavorite(favModel)
    }

}