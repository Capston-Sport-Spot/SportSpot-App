package com.example.sportspot.view.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportspot.databinding.ActivityFutsalBinding
import com.example.sportspot.response.FieldResponseItem
import com.example.sportspot.view.ViewModelFactory
import com.example.sportspot.view.adapter.FieldAdapter

class FutsalActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var fieldAdapter: FieldAdapter

    private var _binding: ActivityFutsalBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFutsalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvField.layoutManager = layoutManager

        fieldAdapter = FieldAdapter()
        binding.rvField.adapter = fieldAdapter

        viewModel.isLoading.observe(this) { state ->
            showLoading(state)
        }

        viewModel.getLapangan().observe(this) { lapangans ->
            Log.e("tes","tesss")
            setLapanganList(lapangans)
        }
    }

    private fun setLapanganList(lapangans: List<FieldResponseItem>?) {
        val adapter = FieldAdapter()
        adapter.submitList(lapangans)
        binding.rvField.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

