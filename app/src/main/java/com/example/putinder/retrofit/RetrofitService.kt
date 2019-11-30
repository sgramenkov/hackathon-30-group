package com.example.putinder.retrofit

import com.example.putinder.retrofit.Models.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET
    suspend fun getData(){
    }
    @GET("/photos")
    suspend fun TransferToPhotosActivity(): Response<List<Photos>>
    @POST
    suspend fun postData(){

    }
}