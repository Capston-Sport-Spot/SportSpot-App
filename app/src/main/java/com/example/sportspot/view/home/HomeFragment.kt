package com.example.sportspot.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportspot.databinding.FragmentHomeBinding
import com.example.sportspot.response.FieldResponseItem
import com.example.sportspot.view.ViewModelFactory
import com.example.sportspot.view.adapter.FieldAdapter
import com.example.sportspot.view.main.BadmintonActivity
import com.example.sportspot.view.main.BasketActivity
import com.example.sportspot.view.main.FutsalActivity
import com.example.sportspot.view.main.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var fieldAdapter: FieldAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonFutsal.setOnClickListener {
            startFutsalActivity()
        }

        binding.buttonBadminton.setOnClickListener {
            startBadmintonActivity()
        }

        binding.buttonBasketball.setOnClickListener {
            startBasketActivity()
        }

        setupRecyclerView()
        observeViewModel()

        return view
    }

    private fun setupRecyclerView() {
        fieldAdapter = FieldAdapter()
        binding.rvField.layoutManager = LinearLayoutManager(requireContext())
        binding.rvField.adapter = fieldAdapter
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { state ->
            showLoading(state)
        }

        viewModel.getLapangan().observe(viewLifecycleOwner) { lapangans ->
            Log.e("HomeFragment", "Lapangans: $lapangans")
            setLapanganList(lapangans)
        }
    }

    private fun setLapanganList(lapangans: List<FieldResponseItem>?) {
        fieldAdapter.submitList(lapangans)
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun startFutsalActivity() {
        startActivity(Intent(requireActivity(), FutsalActivity::class.java))
    }

    private fun startBadmintonActivity() {
        startActivity(Intent(requireActivity(), BadmintonActivity::class.java))
    }

    private fun startBasketActivity() {
        startActivity(Intent(requireActivity(), BasketActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
