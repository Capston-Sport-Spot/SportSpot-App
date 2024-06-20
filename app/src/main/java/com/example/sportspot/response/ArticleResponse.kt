package com.example.sportspot.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("ArticleResponse")
	val articleResponse: List<ArticleResponseItem?>? = null
)

data class ArticleResponseItem(

	@field:SerializedName("imageLink")
	val imageLink: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("Link")
	val link: String? = null
)
