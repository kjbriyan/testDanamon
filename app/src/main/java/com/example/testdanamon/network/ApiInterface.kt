package com.example.testdanamon.network

import com.example.testdanamon.model.ResponseUser
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getData(): Call<List<ResponseUser>>
}