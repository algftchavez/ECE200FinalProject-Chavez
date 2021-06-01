package com.example.retrofit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.R
import com.example.retrofit.data.api.ApiHelper
import com.example.retrofit.data.api.RetrofitBuilder
import com.example.retrofit.data.model.Article
import com.example.retrofit.databinding.FragmentHomeBinding
import com.example.retrofit.utils.Result

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>(factoryProducer = {
        ViewModelFactory(
            ApiHelper(RetrofitBuilder.apiService)
        )
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()
        observeNews()
    }

    private fun observeNews() {
        viewModel.newsResult.observe(viewLifecycleOwner) { result ->
            Log.d("HomeFragment", "Result $result")
            when (result) {
                is Result.Loading -> { binding.pb.visibility = View.VISIBLE }
                is Result.Success -> { onSuccess(result.data) }
                is Result.Error -> { onError(result.errorMessage) }
            }
        }
    }

    private fun onSuccess(articles: List<Article>) {
        binding.pb.visibility = View.GONE

        val adapter = ArticleAdapter(articles) { article ->
            Toast.makeText(context, "Article Clicked!", Toast.LENGTH_SHORT).show()
            navigateToDetail(article)
        }
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = adapter
    }

    private fun onError(errorMessage: String) {
        binding.pb.visibility = View.GONE
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetail (articles: Article){
        val bundle = Bundle ()
        bundle.putParcelable(ARTICLE_KEY, articles)
        findNavController().navigate(R.id.action_to_detail_fragment, bundle)
    }

    companion object {
        const val ARTICLE_KEY = "ARTICLE"
    }
}