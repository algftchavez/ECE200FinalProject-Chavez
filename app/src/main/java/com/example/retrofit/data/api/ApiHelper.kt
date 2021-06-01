package com.example.retrofit.data.api

/**
 * Helper class
 */
class ApiHelper(private val api: NewsApi) {
    suspend fun getNews(query: String, apiKey: String) = api.getNews(query, apiKey)
}