package com.example.retrofit.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Class for initializing Retrofit in our application
 */
object RetrofitBuilder {

    private const val BASE_URL = "https://newsapi.org/v2/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .client(getHttpClient())
            .build()
    }

    /**
     * For adding logs of the API requests and response
     */
    private fun getHttpClient(): OkHttpClient {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return  OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    /**
     * Moshi is for serializing our data model to JSON and vice versa
     */
    private fun getMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val apiService: NewsApi = getRetrofit().create(NewsApi::class.java)
}