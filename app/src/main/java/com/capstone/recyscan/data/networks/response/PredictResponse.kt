package com.capstone.recyscan.data.networks.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("priceBefore")
	val priceBefore: String? = null,

	@field:SerializedName("youtubeLink")
	val youtubeLink: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("priceAfter")
	val priceAfter: String? = null
)
