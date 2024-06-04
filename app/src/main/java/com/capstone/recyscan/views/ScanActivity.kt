package com.capstone.recyscan.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(mToolbar);
            mToolbar.apply {
                setNavigationIcon(R.drawable.back_icon);
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            btnResultScan.setOnClickListener {
                startActivity(Intent(this@ScanActivity, ResultActivity::class.java))
            }

        }

    }
}