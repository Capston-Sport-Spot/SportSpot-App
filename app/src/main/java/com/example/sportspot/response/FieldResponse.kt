package com.example.sportspot.response

import com.google.gson.annotations.SerializedName

data class FieldResponse(

	@field:SerializedName("message")
	val message: String? = null
)
