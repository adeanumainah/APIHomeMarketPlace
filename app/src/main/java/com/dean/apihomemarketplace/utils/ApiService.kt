package com.dean.apihomemarketplace.utils

import android.accessibilityservice.GestureDescription
import com.dean.apihomemarketplace.model.DataItem
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    val BASE_URL: String = "http://192.168.88.236:8080/api/"

//    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    val client = OkHttpClient.Builder().addInterceptor(interceptor)
//            .retryOnConnectionFailure(true)
//            .connectTimeout(15, TimeUnit.SECONDS).build()
    val gson = GsonBuilder().setLenient().create()
    val endpoint: ApiEndPoint
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client).addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

//        fun getInstace(): ApiEndPoint = retrofit.create(ApiEndPoint::class.java)
            return retrofit.create(ApiEndPoint::class.java)

        }



}



