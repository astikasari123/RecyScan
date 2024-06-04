package com.capstone.recyscan.views

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.capstone.recyscan.R
import com.capstone.recyscan.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(mToolbar);
            mToolbar.apply {
                setNavigationIcon(R.drawable.back_icon);
                setNavigationOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_edit -> {
                startActivity(Intent(this@ProfileEditActivity, ProfileActivity::class.java))
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}