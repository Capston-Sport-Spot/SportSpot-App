package com.example.sportspot.view.register

import androidx.lifecycle.ViewModel
import com.example.sportspot.repository.UserRepository
import com.example.sportspot.response.RegisterResponse
import retrofit2.Response

class RegisterViewModel(private var repository: UserRepository) : ViewModel() {
    suspend fun register(email: String, password: String, displayName: String, alamat: String, kota: String, hp: String): RegisterResponse {
        return repository.register(email, password, displayName, alamat, kota, hp)
    }

}
