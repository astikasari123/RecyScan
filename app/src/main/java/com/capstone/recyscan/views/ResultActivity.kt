package com.capstone.recyscan.views

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    @SuppressLint("SetTextI18n")
    private fun setupViewModel(uri: Uri) {
        uploadImageViewModel.apply {
            uploadSuccess.observe(this@ResultActivity) {
                binding.apply {
                    it.data.let {
                        tvResultWaste.text = it?.result
                        edtAreaCount.setText("${it?.priceAfter} \n ${it?.priceBefore}")
                        val youtubeLinkText = it?.youtubeLink
                        edtAreaRecommendation.setText(youtubeLinkText)
                        Linkify.addLinks(edtAreaRecommendation, Linkify.WEB_URLS)
                        edtAreaRecommendation.movementMethod = android.text.method.LinkMovementMethod.getInstance()
                        edtAreaRecommendation.keyListener = null
                        edtAreaRecommendation.isFocusable = false
                        edtAreaRecommendation.isClickable = true
                    }

                }
            }
            uploadError.observe(this@ResultActivity) {
                Toast.makeText(this@ResultActivity, it, Toast.LENGTH_SHORT).show()
            }
            isLoading.observe(this@ResultActivity) {
                binding.apply {
                    val loadingText = "Memuat hasil prediksi"
                    if (it)
                    {
                        tvResultWaste.text = loadingText
                        edtAreaCount.setText(loadingText)
                        edtAreaRecommendation.setText(loadingText)
                    } else {
                        tvResultWaste.text = ""
                        edtAreaCount.setText("")
                        edtAreaRecommendation.setText("")
                    }
                }
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