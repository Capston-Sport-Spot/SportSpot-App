package com.example.sportspot.view.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportspot.databinding.ActivityDetailArticleBinding
import com.example.sportspot.response.ArticleResponseItem


class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val data = intent.getParcelableExtra<ArticleResponseItem>("DATA")
//
//        binding.tvTitleTips.text = data?.name
//        binding.tvDescTips.text = data?.description
//        data?.photo?.let { binding.ivTips.setImageResource(it) }

    }
}