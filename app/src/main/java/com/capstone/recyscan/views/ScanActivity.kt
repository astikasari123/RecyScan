package com.capstone.recyscan.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.ActivityScanBinding
import com.capstone.recyscan.viewmodel.UploadImageViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.nio.file.Files.createFile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private var imageCapture: ImageCapture? = null
    private var cameraProvider: ProcessCameraProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setupToolBar()
            val cameraProviderFuture = ProcessCameraProvider.getInstance(this@ScanActivity)
            cameraProviderFuture.addListener({
                cameraProvider = cameraProviderFuture.get()
                bindCameraUseCases()
            }, ContextCompat.getMainExecutor(this@ScanActivity))

            btnResultScan.setOnClickListener {
                takePhoto()
            }

        }

    }

    private fun ActivityScanBinding.setupToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.apply {
            setNavigationIcon(R.drawable.back_icon);
            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun ActivityScanBinding.bindCameraUseCases() {
        val rotation = viewFinder.display.rotation

        val cameraProvider = cameraProvider
            ?: throw IllegalStateException("Camera initialization failed.")

        val preview = Preview.Builder()
            .setTargetRotation(rotation)
            .build()

        imageCapture = ImageCapture.Builder()
            .setTargetRotation(rotation)
            .build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this@ScanActivity,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )
            preview.setSurfaceProvider(viewFinder.surfaceProvider)
        } catch (ex: Exception) {
            Log.e("", "Use case binding failed", ex)
        }
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = createFile()

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this@ScanActivity),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                    Toast.makeText(this@ScanActivity, "Foto berhasil diambil", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(
                            this@ScanActivity,
                            ResultActivity::class.java
                        ).apply {
                            putExtra(ResultActivity.IMAGE_URI_VALUE, savedUri.toString())
                            putExtra(ResultActivity.TOOLBAR_TITLE, true)
                        }
                    )
                }

                override fun onError(exception: ImageCaptureException) {
                    val message = "Photo capture failed: ${exception.message}"
                    Toast.makeText(this@ScanActivity, "Terjadi kesalahan: Foto gagal diambil", Toast.LENGTH_SHORT).show()

                }
            }
        )
    }

    private fun createFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDirectory = externalMediaDirs.firstOrNull()
        return File.createTempFile("IMG_${timestamp}_", ".jpg", storageDirectory)
    }

}