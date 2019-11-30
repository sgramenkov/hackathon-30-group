package com.example.putinder.retrofit

import com.example.putinder.Retrofit.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {
    val BASE_URL="https://jsonplaceholder.typicode.com/"
    fun makeRetrofitService(): RetrofitService {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}