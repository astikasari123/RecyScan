package com.capstone.recyscan.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(mToolbar);
            mToolbar.apply {
                setNavigationIcon(R.drawable.back_icon);
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            ivResult.setImageDrawable(
                ContextCompat.getDrawable(
                    this@ResultActivity,
                    R.drawable.img_sample_user
                )
            )
        }

    }
}