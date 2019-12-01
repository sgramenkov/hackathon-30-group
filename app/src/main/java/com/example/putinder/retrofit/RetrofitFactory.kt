package com.example.putinder.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {
    //val BASE_URL="https://jsonplaceholder.typicode.com/"
    val BASE_URL="http://35.223.172.175:8080/"
    fun makeRetrofitService(): RetrofitService {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}