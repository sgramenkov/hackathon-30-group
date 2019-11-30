package com.example.putinder.Retrofit

import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET
    suspend fun getData(){

    }
    @POST
    suspend fun postData(){
        
    }
}