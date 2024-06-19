package com.capstone.recyscan.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.recyscan.data.networks.api.ApiConfig
import com.capstone.recyscan.data.networks.response.PredictResponse
import com.capstone.recyscan.utils.reduceFileImage
import com.capstone.recyscan.utils.uriToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException

class UploadImageViewModel: ViewModel() {
    private val _uploadSuccess = MutableLiveData<PredictResponse>()
    val uploadSuccess: LiveData<PredictResponse> = _uploadSuccess

    private val _uploadError = MutableLiveData<String>()
    val uploadError: LiveData<String> = _uploadError

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun uploadImage(context: Context, imageUri: Uri) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                // Convert URI to File
                val file = uriToFile(imageUri, context)

                // Reduce file size if necessary
                val reducedFile = file.reduceFileImage()

                // Create MultipartBody.Part using the file
                val requestFile = reducedFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", reducedFile.name, requestFile)

                // Make API call
                _isLoading.value = false
                val response = ApiConfig.getApiService().uploadImage(body)
                _uploadSuccess.postValue(response)

            } catch (e: Exception) {
                if (e is HttpException) {
                    _isLoading.value = false

                    val errorMessage = e.response()?.errorBody()?.string()

                    _uploadError.postValue(errorMessage.toString())
                } else {
                    _isLoading.value = false
                    _uploadError.postValue(e.message.toString())
                }
            }
        }
    }
}