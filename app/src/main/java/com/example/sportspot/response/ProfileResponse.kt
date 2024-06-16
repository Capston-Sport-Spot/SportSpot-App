package com.example.sportspot.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("createdAt")
	val createdAt: CreatedAt? = null,

	@field:SerializedName("kota")
	val kota: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("hp")
	val hp: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)

data class CreatedAt(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
)
