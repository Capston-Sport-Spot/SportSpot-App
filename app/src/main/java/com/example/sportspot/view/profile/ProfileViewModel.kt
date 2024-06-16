package com.example.sportspot.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportspot.repository.UserRepository
import com.example.sportspot.response.ProfileResponse
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    val profile: LiveData<ProfileResponse> = userRepository.profileResponse

    fun fetchProfile() {
        viewModelScope.launch {
            userRepository.getProfile()
        }
    }
}