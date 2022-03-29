package com.example.testdanamon.network

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class InitRetrofit {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(2,TimeUnit.MINUTES)
        .connectTimeout(1,TimeUnit.MINUTES)
        .build()


    fun initRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    fun getInstance(): ApiInterface{
        return initRetrofit().create(ApiInterface::class.java)
    }

}