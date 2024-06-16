package com.example.sportspot.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import android.provider.Settings
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sportspot.R
import com.example.sportspot.databinding.FragmentProfileBinding
import com.example.sportspot.view.ViewModelFactory
import com.example.sportspot.view.main.MainViewModel


class ProfileFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        viewModel.getSession().observe(requireActivity()) { user ->
            binding.tvProfileName.text = user.displayName
        }
        viewModel.getSession().observe(requireActivity()) { user ->
            binding.tvProfileEmail.text = user.email
        }
        viewModel.getSession().observe(requireActivity()) { user ->
            binding.tvProfilePhone.text = user.hp
        }
        viewModel.getSession().observe(requireActivity()) { user ->
            binding.tvProfileAddress.text = user.alamat
        }
        viewModel.getSession().observe(requireActivity()) { user ->
            binding.tvProfileCity.text = user.kota
        }

        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(getString(R.string.are_you_sure_want_to_logout))
            builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                viewModel.logout()
                dialog.dismiss()
            }
            builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}