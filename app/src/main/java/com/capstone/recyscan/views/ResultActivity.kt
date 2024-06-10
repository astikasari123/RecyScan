package com.capstone.recyscan.views

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var currentUriValue: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater).apply {
            setContentView(root)
            val args = Uri.parse(intent.getStringExtra(IMAGE_URI_VALUE))
            currentUriValue = args
            setSupportActionBar(mToolbar);
            mToolbar.apply {
                setNavigationIcon(R.drawable.back_icon);
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            Glide.with(this@ResultActivity)
                .load(currentUriValue)
                .into(ivResult)
        }

    }
    companion object {
        const val IMAGE_URI_VALUE = "image_uri_value"
    }
}