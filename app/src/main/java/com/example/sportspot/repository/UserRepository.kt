package com.example.sportspot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportspot.api.ApiService
import com.example.sportspot.preferences.UserPreferences
import com.example.sportspot.response.RegisterResponse
import retrofit2.Response

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreferences
) {
//    private var _loginResponse = MutableLiveData<LoginResponse>()
//    var loginResponse: MutableLiveData<LoginResponse> = _loginResponse

    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    suspend fun register(email: String, password: String, displayName: String, alamat: String, kota: String, hp: String): RegisterResponse {
        return apiService.register(email, password, displayName, alamat, kota, hp)
    }

//    fun login(username: String, password: String) {
//        _isLoading.value = true
//        val client = apiService.login(username, password)
//        client.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    _isLoading.value = false
//                    _loginResponse.value = response.body()
//                } else {
//                    val errorMessage = extractErrorMessage(response)
//                    Log.e("UserRepo", errorMessage)
//                    _isLoading.value = false
//                    _loginResponse.value = LoginResponse(message = errorMessage)
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                val errorMessage = "Login failed: ${t.message}"
//                Log.e("UserRepo", errorMessage)
//                _isLoading.value = false
//                _loginResponse.value = LoginResponse(message = errorMessage)
//            }
//        })
//    }

//    private fun extractErrorMessage(response: Response<*>): String {
//        return try {
//            val errorObject =
//                Gson().fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
//            errorObject.message ?: "Login failed: ${response.message()}"
//        } catch (e: Exception) {
//            "Login failed: ${response.message()}"
//        }
//    }
//
//    fun getSession(): Flow<UserModel> {
//        return userPreference.getSession()
//    }
//
//    suspend fun saveSession(user: UserModel) {
//        userPreference.saveSession(user)
//    }
//
//    suspend fun logout() {
//        userPreference.logout()
//    }

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