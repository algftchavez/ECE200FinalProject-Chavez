package com.example.retrofit.data.repository

import com.example.retrofit.data.api.ApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class, functions for calling out API
 */
class NewsRepository(private val apiHelper: ApiHelper) {
    suspend fun getNews(query: String, apiKey: String) = apiHelper.getNews(query, apiKey)
    }
