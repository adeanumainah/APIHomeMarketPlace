package com.dean.apihomemarketplace.utils

import com.dean.apihomemarketplace.model.ResponseHome
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("data")
    fun getData(): Call<ResponseHome>
}