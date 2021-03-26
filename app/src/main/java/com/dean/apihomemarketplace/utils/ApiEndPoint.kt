package com.dean.apihomemarketplace.utils

import com.dean.apihomemarketplace.model.ResponseHome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("data")
    fun getData(): Call<ResponseHome>

    @GET("search")
    fun searchItem(@Query("search") data: String?): Call<ResponseHome?>?

}