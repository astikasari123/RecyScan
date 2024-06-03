package com.capstone.recyscan.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.recyscan.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            setContentView(root)

        }
    }
}