package com.example.sportspot.preferences

data class UserModel(
    val uid: String,
    val email: String,
    val displayName: String,
    val token: String,
    val isLogin: Boolean = false
)

