package com.capstone.recyscan.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.capstone.recyscan.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater).apply {
            setContentView(root)
            AnimatorSet().apply {
                playSequentially(
                    ObjectAnimator.ofFloat(ivAppLogo, View.ALPHA, 1f).setDuration(1000),
                    ObjectAnimator.ofFloat(tvHeadline1, View.ALPHA, 1f).setDuration(700),
                    ObjectAnimator.ofFloat(tvHeadline2, View.ALPHA, 1f).setDuration(700),
                )
                start()
            }
            lifecycleScope.launch {
                delay(SPLASH_SCREEN_DURATION.seconds)
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }

        }

    }
    companion object{
        const val SPLASH_SCREEN_DURATION = 3
    }
}