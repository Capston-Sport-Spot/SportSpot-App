package com.example.sportspot.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FieldResponse(

	@field:SerializedName("FieldResponse")
	val fieldResponse: List<FieldResponseItem> = emptyList()
) : Parcelable

@Parcelize
data class SubFieldsItem(

	@field:SerializedName("fieldName")
	val fieldName: String? = null,

	@field:SerializedName("facilities")
	val facilities: List<String?>? = null,

	@field:SerializedName("pricePerSession")
	val pricePerSession: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
) : Parcelable

@Parcelize
data class FieldResponseItem(

	@field:SerializedName("lapanganName")
	val lapanganName: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("openingHours")
	val openingHours: OpeningHours? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("subFields")
	val subFields: List<SubFieldsItem?>? = null,

	@field:SerializedName("lapanganType")
	val lapanganType: String? = null,

	@field:SerializedName("kota")
	val kota: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
) : Parcelable

@Parcelize
data class OpeningHours(

	@field:SerializedName("close")
	val close: String? = null,

	@field:SerializedName("open")
	val open: String? = null,

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("end")
	val end: String? = null
) : Parcelable
