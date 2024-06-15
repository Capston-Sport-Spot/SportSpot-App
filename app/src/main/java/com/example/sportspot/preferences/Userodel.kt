package com.example.sportspot.preferences

data class UserModel(
    val periodTime: String,
    val id: String,
    val email: String,
    val username: String,
    val token: String,
    val isLogin: Boolean = false
)

