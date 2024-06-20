package com.capstone.recyscan.data.networks.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
)

data class ReviewsItem(

	@field:SerializedName("originalText")
	val originalText: OriginalText? = null,

	@field:SerializedName("publishTime")
	val publishTime: String? = null,

	@field:SerializedName("sentiment")
	val sentiment: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("relativePublishTimeDescription")
	val relativePublishTimeDescription: String? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("authorAttribution")
	val authorAttribution: AuthorAttribution? = null
)

data class Text(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("languageCode")
	val languageCode: String? = null
)

data class AuthorAttributionsItem(

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("photoUri")
	val photoUri: String? = null,

	@field:SerializedName("uri")
	val uri: String? = null
)

data class ResponseItem(

	@field:SerializedName("formattedAddress")
	val formattedAddress: String? = null,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem?>? = null,

	@field:SerializedName("displayName")
	val displayName: DisplayName? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("photos")
	val photos: List<PhotosItem?>? = null,

	@field:SerializedName("averageSentiment")
	val averageSentiment: Any? = null
)

data class Location(

	@field:SerializedName("latitude")
	val latitude: Any? = null,

	@field:SerializedName("longitude")
	val longitude: Any? = null
)

data class DisplayName(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("languageCode")
	val languageCode: String? = null
)

data class PhotosItem(

	@field:SerializedName("authorAttributions")
	val authorAttributions: List<AuthorAttributionsItem?>? = null,

	@field:SerializedName("widthPx")
	val widthPx: Int? = null,

	@field:SerializedName("heightPx")
	val heightPx: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class OriginalText(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("languageCode")
	val languageCode: String? = null
)

data class AuthorAttribution(

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("photoUri")
	val photoUri: String? = null,

	@field:SerializedName("uri")
	val uri: String? = null
)
