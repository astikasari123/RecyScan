package com.capstone.recyscan.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.recyscan.R
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.databinding.ActivityResultBinding
import com.capstone.recyscan.viewmodel.HistoryViewModel
import com.capstone.recyscan.viewmodel.UploadImageViewModel
import com.capstone.recyscan.viewmodelfactory.HistoryViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var currentUriValue: Uri
    private var isResult: Boolean = false
    private val uploadImageViewModel: UploadImageViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory.getInstanceOfHistoryViewModelFactory(this@ResultActivity)
    }

    private var scanResult: String = ""
    private var countResult: String = ""
    private var recommendResult: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = Uri.parse(intent.getStringExtra(IMAGE_URI_VALUE))
        isResult = intent.getBooleanExtra(TOOLBAR_TITLE, false)
        currentUriValue = args

        setupToolBar()

        Glide.with(this@ResultActivity)
            .load(currentUriValue)
            .into(binding.ivResult)

        if (isResult) {
            setupViewModel(currentUriValue)
            setupButtonAction()
        } else {
            binding.btnSave.visibility = View.GONE
            setTextAction(
                intent.getStringExtra(SCAN_RESULT).toString(),
                intent.getStringExtra(COUNT_RESULT).toString(),
                intent.getStringExtra(RECOMMEND_RESULT).toString()
            )
        }
    }

    private fun setupButtonAction() {
        binding.apply {

            btnSave.setOnClickListener {
                historyViewModel.insertHistory(
                    EntityHistory(
                        scanResult = scanResult,
                        countResult = countResult,
                        recommendResult = recommendResult,
                        image = currentUriValue.toString(),
                        date = SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time)
                    )
                )
                Toast.makeText(
                    this@ResultActivity,
                    "Hasil pindai tersimpan di riwayat",
                    Toast.LENGTH_SHORT
                ).show()
                it.isEnabled = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupViewModel(uri: Uri) {
        uploadImageViewModel.apply {
            isLoading.observe(this@ResultActivity) {
                binding.apply {
                    val loadingText = "Memuat hasil prediksi"
                    if (it) {
                        tvResultWaste.text = loadingText
                        edtAreaCount.setText(loadingText)
                        edtAreaRecommendation.setText(loadingText)
                    }
                    btnSave.visibility = if (it) View.INVISIBLE else View.VISIBLE
                }
            }
            uploadSuccess.observe(this@ResultActivity) { response ->
                if (response.data?.result!!.isNotEmpty()) {
                    binding.apply {
                        response.data.let {
                            it.apply {
                                scanResult = result.toString()
                                countResult = "$priceAfter \n $priceBefore"
                                recommendResult = youtubeLink.toString()
                                setTextAction(scanResult, countResult, recommendResult)
                            }
                        }
                    }
                }
            }
            uploadError.observe(this@ResultActivity) {
                if (it.isNotEmpty()) {
                    intentToHome()
                    Toast.makeText(
                        this@ResultActivity,
                        "Terjadi kesalahan: Foto gagal terpindai",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            uploadImage(this@ResultActivity, uri)
        }
    }

    private fun setTextAction(result: String, count: String, recommend: String) {
        binding.apply {
            tvResultWaste.text = result
            edtAreaCount.setText(count)
            edtAreaRecommendation.setText(recommend)
            Linkify.addLinks(edtAreaRecommendation, Linkify.WEB_URLS)
            edtAreaRecommendation.movementMethod = LinkMovementMethod.getInstance()
            edtAreaRecommendation.keyListener = null
            edtAreaRecommendation.isFocusable = false
            edtAreaRecommendation.isClickable = true
        }
    }

    private fun setupToolBar() {
        binding.apply {

            setSupportActionBar(mToolbar)
            mToolbar.apply {
                title = if (!isResult) "Detail Pindaian" else "Hasil"
                setNavigationIcon(R.drawable.back_icon)
                setNavigationOnClickListener {
                    if (!isResult) {
                        onBackPressedDispatcher.onBackPressed()
                    } else {
                       intentToHome()
                    }
                }
            }
        }
    }
    private fun intentToHome(){
        startActivity(Intent(this@ResultActivity, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    companion object {
        const val IMAGE_URI_VALUE = "image_uri_value"
        const val TOOLBAR_TITLE = "toolbar_title"
        const val SCAN_RESULT = "scan_result"
        const val COUNT_RESULT = "count_result"
        const val RECOMMEND_RESULT = "recommend_result"
    }
}
