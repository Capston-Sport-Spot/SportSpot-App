package com.example.sportspot.view.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sportspot.R
import com.example.sportspot.databinding.ActivityLoginBinding
import com.example.sportspot.preferences.UserModel
import com.example.sportspot.response.ErrorResponse
import com.example.sportspot.view.ViewModelFactory
import com.example.sportspot.view.main.MainActivity
import com.example.sportspot.view.register.RegisterActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.loginResponse.observe(this) { loginResponse ->
            if (loginResponse.message == "Login successful") {
                showToast(getString(R.string.login_successful))
                save(
                    UserModel(
                        loginResponse.user?.uid.toString(),
                        loginResponse.user?.email.toString(),
                        loginResponse.user?.displayName.toString(),
                        loginResponse.token.toString(),
                        true
                    )
                )
            } else {
                showToast(loginResponse.message)
            }
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            try {
                viewModel.isLoading.observe(this) {
                    showLoading(it)
                }

                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (email.isEmpty()) {
                    binding.emailEditText.error = getString(R.string.email_cannot_be_empty)
                } else if (password.isEmpty()) {
                    binding.passwordEditText.error = getString(R.string.password_cannot_be_empty)
                }

                viewModel.login(email, password)

            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                showToast(errorMessage)
            }
        }
    }

    private fun save(session: UserModel) {
        lifecycleScope.launch {
            viewModel.saveSession(session)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ViewModelFactory.clearInstance()
            startActivity(intent)
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.loginButton.isEnabled = !isLoading
    }

}