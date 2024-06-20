package com.example.sportspot.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sportspot.databinding.ActivityDetailFieldBinding
import com.example.sportspot.response.FieldResponseItem
import com.example.sportspot.response.SubFieldsItem
import com.example.sportspot.view.adapter.SubFieldsAdapter
import com.example.sportspot.view.main.LinearLayoutManager

class DetailFieldActivity : AppCompatActivity() {
    private var _binding: ActivityDetailFieldBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fieldItem = intent.getParcelableExtra<FieldResponseItem>(DETAIL_FIELD)
        fieldItem?.let { setupData(it) }
    }

    private fun setupData(fieldItem: FieldResponseItem) {
        Glide.with(this)
            .load(fieldItem.imageUrl)
            .into(binding.ivImage)

        binding.tvName.text = fieldItem.lapanganName
        binding.tvLocation.text = fieldItem.location
        binding.tvOpeningHours.text = "${fieldItem.openingHours?.open ?: "N/A"} - ${fieldItem.openingHours?.close ?: "N/A"}"
        binding.tvLapanganType.text = fieldItem.lapanganType
        binding.tvKota.text = fieldItem.kota
        binding.tvAlamat.text = fieldItem.alamat

        fieldItem.subFields?.let { subFields ->
            setupSubFieldsRecyclerView(subFields)
        }
    }

    private fun setupSubFieldsRecyclerView(subFields: List<SubFieldsItem?>) {
        val subFieldsAdapter = SubFieldsAdapter(subFields)
        binding.rvSubFields.apply {
            layoutManager = LinearLayoutManager(this@DetailFieldActivity)
            adapter = subFieldsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DETAIL_FIELD = "detail_field"
    }
}
