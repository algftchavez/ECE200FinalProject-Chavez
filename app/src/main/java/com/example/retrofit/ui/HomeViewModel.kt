package com.example.retrofit.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.model.Article
import com.example.retrofit.data.repository.NewsRepository
import com.example.retrofit.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: NewsRepository): ViewModel() {

    val newsResult = MutableLiveData<Result<List<Article>>>()

    fun getNews() = viewModelScope.launch {
        newsResult.postValue(Result.Loading)
        try {
            // TODO: put your own query and API key from https://newsapi.org/account
            val response = repository.getNews("q", "daa182812e764112b7198bc06ca3d072")
            if (response.isSuccessful) {
                newsResult.postValue(Result.Success(response.body()?.articles.orEmpty()))
            } else {
                newsResult.postValue(Result.Error("Error calling the API"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            newsResult.postValue(Result.Error(e.localizedMessage.orEmpty()))
        }
    }
}