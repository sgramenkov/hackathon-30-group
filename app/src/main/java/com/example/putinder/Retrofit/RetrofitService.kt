package com.example.putinder.Retrofit

import com.example.putinder.Retrofit.Models.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET
    suspend fun getData(){
    }
    @GET("/photos")
    suspend fun TransferToPhotosActivity(): Response<ArrayList<Photos>>
    @POST
    suspend fun postData(){

    }
}