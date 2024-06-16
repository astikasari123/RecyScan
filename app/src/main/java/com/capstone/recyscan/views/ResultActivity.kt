package com.capstone.recyscan.views

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.ActivityResultBinding
import com.capstone.recyscan.viewmodel.UploadImageViewModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var currentUriValue: Uri
    private var isResult: Boolean = false
    private val uploadImageViewModel: UploadImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater).apply {
            setContentView(root)
            val args = Uri.parse(intent.getStringExtra(IMAGE_URI_VALUE))
            isResult = intent.getBooleanExtra(TOOLBAR_TITLE, false)
            currentUriValue = args
            setupToolBar()
            Glide.with(this@ResultActivity)
                .load(currentUriValue)
                .into(ivResult)
            setupViewModel(currentUriValue)
        }

    }

    private fun setupViewModel(uri: Uri) {
        uploadImageViewModel.apply {
            uploadSuccess.observe(this@ResultActivity) {
                binding.apply {
                    tvResultWaste.text = it.data?.result
                    it.data?.suggestion?.let { it1 -> wvSuggestion.loadData(it1, "text/html", "UTF-8") }
                }
            }
            uploadError.observe(this@ResultActivity) {
                Toast.makeText(this@ResultActivity, it, Toast.LENGTH_SHORT).show()
            }
            uploadImage(this@ResultActivity, uri)
        }
    }

    private fun ActivityResultBinding.setupToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.apply {
            title = if (isResult == true) "Hasil" else "Detail Pindaian"
            setNavigationIcon(R.drawable.back_icon);
            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    companion object {
        const val IMAGE_URI_VALUE = "image_uri_value"
        const val TOOLBAR_TITLE = "toolbar_title"
    }
}