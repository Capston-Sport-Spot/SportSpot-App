package com.example.sportspot.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportspot.api.ApiService
import com.example.sportspot.preferences.UserModel
import com.example.sportspot.preferences.UserPreferences
import com.example.sportspot.response.ErrorResponse
import com.example.sportspot.response.FieldResponse
import com.example.sportspot.response.FieldResponseItem
import com.example.sportspot.response.LoginResponse
import com.example.sportspot.response.ProfileResponse
import com.example.sportspot.response.RegisterResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreferences
) {
    private var _loginResponse = MutableLiveData<LoginResponse>()
    var loginResponse: MutableLiveData<LoginResponse> = _loginResponse

    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private val _profileResponse = MutableLiveData<ProfileResponse>()
    val profileResponse: LiveData<ProfileResponse> = _profileResponse

    private var _list = MutableLiveData<List<FieldResponseItem>>()
    var list: MutableLiveData<List<FieldResponseItem>> = _list

    suspend fun register(email: String, password: String, displayName: String, alamat: String, kota: String, hp: String): RegisterResponse {
        return apiService.register(email, password, displayName, alamat, kota, hp)
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        val client = apiService.login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _loginResponse.value = response.body()
                } else {
                    val errorMessage = extractErrorMessage(response)
                    Log.e("UserRepo", errorMessage)
                    _isLoading.value = false
                    _loginResponse.value = LoginResponse(message = errorMessage)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                val errorMessage = "Login failed: ${t.message}"
                Log.e("UserRepo", errorMessage)
                _isLoading.value = false
                _loginResponse.value = LoginResponse(message = errorMessage)
            }
        })
    }

    suspend fun getProfile() {
        val token = getBearerToken()
        val call = apiService.getProfile(token)

        call.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    _profileResponse.value = response.body()
                } else {
                    val errorMessage = extractErrorMessage(response)
                    Log.e("UserRepo", "Failed to get profile: $errorMessage")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("UserRepo", "Failed to get profile: ${t.message}")
            }
        })
    }

    private suspend fun getBearerToken(): String {
        val userModel = userPreference.getSession().firstOrNull()
        return "Bearer ${userModel?.token}"
    }

    private fun extractErrorMessage(response: Response<*>): String {
        return try {
            val errorObject =
                Gson().fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
            errorObject.message ?: "Login failed: ${response.message()}"
        } catch (e: Exception) {
            "Login failed: ${response.message()}"
        }
    }

    fun getLapangan() {
        _isLoading.value = true
        val client = apiService.getLapangans()
        client.enqueue(object : Callback<List<FieldResponseItem>> {
            override fun onResponse(
                call: Call<List<FieldResponseItem>>,
                response: Response<List<FieldResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val newLapangans = response.body().orEmpty()
                    val currentList = _list.value.orEmpty()
                    val updatedList = mutableListOf<FieldResponseItem>()

                    updatedList.addAll(newLapangans)
                    updatedList.addAll(currentList)

                    _list.value = updatedList
                    Log.e("Ini sukses","Sukses")
                } else {
                    Log.e("Gagal", "Failed to get lapangans: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FieldResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("Gagal", "Error: ${t.message}")
            }
        })
    }



    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreferences
        ): UserRepository = UserRepository(apiService, userPreference)

        fun clearInstance() {
            instance = null
        }
    }
}
