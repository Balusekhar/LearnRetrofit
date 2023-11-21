package com.example.learnretrofit

import retrofit2.Call
import retrofit2.http.GET

interface MemeInterface {
    @GET("gimme")
    fun getMeme(): Call<MemeData>
}