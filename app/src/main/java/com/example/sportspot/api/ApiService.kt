package com.example.sportspot.api

import com.example.sportspot.response.ArticleResponse
import com.example.sportspot.response.FieldResponse
import com.example.sportspot.response.FieldResponseItem
import com.example.sportspot.response.LoginResponse
import com.example.sportspot.response.ProfileResponse
import com.example.sportspot.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("displayName") displayName: String,
        @Field("alamat") alamat: String,
        @Field("kota") kota: String,
        @Field("hp") hp: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("lapangans")
    fun getLapangans(): Call<List<FieldResponseItem>>

    @GET("profile")
    fun getProfile(
        @Header("Authorization") token: String
    ): Call<ProfileResponse>

    @GET("articles")
    suspend fun getArticles(): ArticleResponse

}
