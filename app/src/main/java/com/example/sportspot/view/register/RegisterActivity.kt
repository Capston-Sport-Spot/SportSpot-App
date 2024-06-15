package com.example.sportspot.view.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.sportspot.R
import com.example.sportspot.databinding.ActivityRegisterBinding
import com.example.sportspot.response.RegisterResponse
import com.example.sportspot.view.ViewModelFactory
import com.example.sportspot.view.login.LoginActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException


class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun setupAction() {
//        binding.loginBtn.setOnClickListener {
//            intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }

        binding.signupButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val displayName = binding.displayNameEditText.text.toString()
            val alamat = binding.alamatEditText.text.toString()
            val kota = binding.kotaEditText.text.toString()
            val hp = binding.nomorhpEditText.text.toString()


            if (email.isEmpty()) {
                binding.emailEditTextLayout.error = getString(R.string.email_cannot_be_empty)
            } else if (password.isEmpty()) {
                binding.passwordEditTextLayout.error = getString(R.string.password_cannot_be_empty)
            } else if (displayName.isEmpty()) {
                binding.displayNameEditTextLayout.error =
                    getString(R.string.displayName_cannot_be_empty)
            } else if (alamat.isEmpty()) {
                binding.alamatEditTextLayout.error = getString(R.string.alamat_cannot_be_empty)
            } else if (kota.isEmpty()) {
                binding.kotaEditTextLayout.error = getString(R.string.kota_cannot_be_empty)
            } else if (hp.isEmpty()) {
                binding.nomorhpEditTextLayout.error = getString(R.string.nomorhp_cannot_be_empty)
            } else {
                showLoading(true)
                lifecycleScope.launch {
                    try {
                        val response = viewModel.register(email, password, displayName, alamat, kota, hp)
                        showLoading(false)
                        showToast(response.message)
                        AlertDialog.Builder(this@RegisterActivity).apply {
                            setMessage(getString(R.string.register_success))
                            setPositiveButton(getString(R.string.continuee)) { _, _ ->
                                val intent = Intent(context, LoginActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                                finish()
                            }
                            create()
                            show()
                        }
                    } catch (e: HttpException) {
                        showLoading(false)
                        val errorBody = e.response()?.errorBody()?.string()
                        val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                        showToast(errorResponse.message)
                    }
                }
            }
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.signupButton.isEnabled = !isLoading
    }
}