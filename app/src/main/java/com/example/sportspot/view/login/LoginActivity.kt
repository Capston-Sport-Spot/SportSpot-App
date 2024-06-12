package com.example.sportspot.view.login

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.sportspot.R
import com.example.sportspot.view.custombutton.EditTextEmail
import com.example.sportspot.view.custombutton.EditTextPassword

class LoginActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var emailEditText: EditTextEmail
    private lateinit var passwordEditText: EditTextPassword
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Menghubungkan variabel dengan view di layout
        progressBar = findViewById(R.id.progressBar)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
        }
    }
}
