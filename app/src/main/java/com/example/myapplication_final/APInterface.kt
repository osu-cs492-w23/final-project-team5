package com.example.myapplication_final

import retrofit2.Call
import retrofit2.http.GET

interface APInterface {
    @GET("activity")
    fun getData(): Call<BoredData>
}