package com.capstone.recyscan.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone.recyscan.databinding.ActivityHomeBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var cameraExecutor: ExecutorService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        binding = ActivityHomeBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnCardScan.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@HomeActivity,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    startActivity(Intent(this@HomeActivity, ScanActivity::class.java))
                } else {
                    ActivityCompat.requestPermissions(
                        this@HomeActivity,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CAMERA_PERMISSION
                    )
                }
            }
            btnCardHistory.setOnClickListener {
                startActivity(Intent(this@HomeActivity, HistoryActivity::class.java))
            }
//            ivUserProfile.setOnClickListener {
//                startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
//            }
        }

    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 123
    }
}