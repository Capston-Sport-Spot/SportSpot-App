package com.example.sportspot.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.sportspot.databinding.ActivityDetailFieldBinding

class DetailFieldActivity : AppCompatActivity() {
    private var _binding: ActivityDetailFieldBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val field = intent.getParcelableExtra<ResponseItem>(DETAIL_FIELD) as ResponseItem
//        setupData(field)

    }

//    private fun setupData(filedItem: ResponseItem) {
//        Glide.with(applicationContext)
//            .load(filedItem.imageUrl)
//            .into(binding.ivAvatar)
//        binding.tvName.text = filedItem.lapanganName
//        binding.tvLocation.text = filedItem.location
//    }

    companion object {
        const val DETAIL_FIELD = "detail_field"
    }
}