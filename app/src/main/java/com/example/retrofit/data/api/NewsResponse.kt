package com.example.retrofit.data.api

import com.example.retrofit.data.model.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.ClientInfoStatus

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val articles: List<Article> = emptyList()
)
