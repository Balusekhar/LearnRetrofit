package com.example.learnretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val url = "https://meme-api.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface by lazy{
        retrofit.create(MemeInterface::class.java)
    }
}


//private val retrofit : MemeInterface by lazy {
//    val retrofit = Retrofit.Builder()
//        .baseUrl(RetrofitInstance.url)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    retrofit.create(MemeInterface::class.java)
//}