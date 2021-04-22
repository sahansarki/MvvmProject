package com.sahan.mvvmproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Sahan
class RetroInstance {
    companion object {
        val baseURL = "https://api.github.com/search/"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}