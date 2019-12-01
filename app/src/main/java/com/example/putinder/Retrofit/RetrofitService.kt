package com.example.putinder.retrofit

import com.example.putinder.retrofit.Models.Sights
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("/places?user_id=2&city=Санкт-Петербург&near=we")
    suspend fun TransferToPhotosActivity(): Response<List<Sights>>


}