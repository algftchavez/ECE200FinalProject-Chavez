package com.example.retrofit.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This class is for Retrofit
 * put your API endpoints here
 */
interface NewsApi {
    @GET("everything")
    suspend fun getNews(@Query("q") query: String, @Query("apiKey") apiKey: String): Response<NewsResponse>
}