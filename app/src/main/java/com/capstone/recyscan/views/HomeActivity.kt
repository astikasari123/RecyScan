package com.capstone.recyscan.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.recyscan.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnCardScan.setOnClickListener {
                startActivity(Intent(this@HomeActivity, ScanActivity::class.java))
            }
        }

    }
}