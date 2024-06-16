package com.example.sportspot.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sportspot.preferences.UserModel
import com.example.sportspot.repository.UserRepository
import com.example.sportspot.response.LoginResponse

class LoginViewModel(private var repository: UserRepository) : ViewModel() {

    val isLoading: LiveData<Boolean>
        get() = repository.isLoading

    val loginResponse: LiveData<LoginResponse>
        get() = repository.loginResponse

    fun login(email: String, password: String) {
        repository.login(email, password)
    }

    suspend fun saveSession(user: UserModel) {
        repository.saveSession(user)
    }

}