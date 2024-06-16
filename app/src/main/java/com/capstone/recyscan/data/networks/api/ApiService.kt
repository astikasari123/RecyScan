package com.capstone.recyscan.data.networks.api

import com.capstone.recyscan.data.networks.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
    ): PredictResponse
}