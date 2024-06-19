package com.example.sportspot.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sportspot.preferences.UserModel
import com.example.sportspot.repository.UserRepository
import com.example.sportspot.response.FieldResponseItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    var isLoading: LiveData<Boolean> = repository.isLoading

    fun getLapangan(): LiveData<List<FieldResponseItem>?> {
        repository.getLapangan()
        return repository.list
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}

