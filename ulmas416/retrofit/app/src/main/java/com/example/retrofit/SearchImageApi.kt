package com.example.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.thecatapi.com"
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val searchImageApi: SearchImageApi = retrofit.create(
        SearchImageApi::class.java
    )
}

interface SearchImageApi {
    @GET(value="/v1/images/search")
    suspend fun getCatImageList(): List<CatImageModel>
}
