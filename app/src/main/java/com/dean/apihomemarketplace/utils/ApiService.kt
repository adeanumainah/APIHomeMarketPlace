package com.dean.apihomemarketplace.utils

import android.accessibilityservice.GestureDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dean.apihomemarketplace.favorite.AppExecutors
import com.dean.apihomemarketplace.favorite.FavModel
import com.dean.apihomemarketplace.model.DataItem
import com.dean.apihomemarketplace.room.FavDao
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService(val dao: FavDao, val appExecutors: AppExecutors) {

    companion object {
        val BASE_URL: String = "http://192.168.50.76:8080/api/"
        private var launchRepository: ApiService? = null
//        val endpoint: ApiEndPoint? = null

        val endpoint: ApiEndPoint
            get() {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    )
                    .build()
//                    .client(client).addConverterFactory(GsonConverterFactory.create(gson))


//        fun getInstace(): ApiEndPoint = retrofit.create(ApiEndPoint::class.java)
                return retrofit.create(ApiEndPoint::class.java)
            }

        @Synchronized
        @JvmStatic
        fun getInstance(dao: FavDao, appExecutors: AppExecutors): ApiService {
            if (launchRepository == null) {
                launchRepository = ApiService(dao, appExecutors)
            }
            return launchRepository as ApiService
        }
    }

    fun saveFavorite(favModel: FavModel): LiveData<FavModel> {
        val saveData = MutableLiveData<FavModel>()
        appExecutors.mainThread().execute {
            dao.insert(favModel)
            saveData.value = favModel
        }
        return saveData
    }

    fun getFavorite(): DataSource.Factory<Int, FavModel> {
        return dao.getAll()
    }



}



