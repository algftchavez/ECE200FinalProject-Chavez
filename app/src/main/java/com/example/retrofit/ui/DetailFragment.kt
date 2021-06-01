package com.example.retrofit.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.retrofit.data.model.Article
import com.example.retrofit.databinding.FragmentDetailBinding
import com.example.retrofit.ui.HomeFragment.Companion.ARTICLE_KEY

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article: Article? = arguments?.getParcelable(ARTICLE_KEY)

        (activity as MainActivity).supportActionBar?.title = article?.source?.name


        article?.let {


            Glide.with(this).load(article.urlToImage).into(binding.ivImage)
            binding.tvTitle.text = article.title
            binding.tvDate.text = article.publishedAt
            binding.tvContent.text = article.content

            binding.ivImage.setOnClickListener {
                val intent = Intent()
                intent.data = Uri.parse(article?.url)
                startActivity(intent)
            }
        }
    }
}