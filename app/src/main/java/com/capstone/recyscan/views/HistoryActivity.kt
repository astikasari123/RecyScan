package com.capstone.recyscan.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.recyscan.R
import androidx.activity.viewModels
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.databinding.ActivityHistoryBinding
import com.capstone.recyscan.viewmodel.HistoryViewModel
import com.capstone.recyscan.viewmodelfactory.HistoryViewModelFactory
import com.capstone.recyscan.views.adapters.AdapterItemHistory
import java.util.Date

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory.getInstanceOfHistoryViewModelFactory(this@HistoryActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setupToolBar()
            setupRecyclerView()
        }
    }

    private fun ActivityHistoryBinding.setupToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.apply {
            setNavigationIcon(R.drawable.back_icon);
            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun ActivityHistoryBinding.setupRecyclerView() {
        rvHistory.layoutManager = LinearLayoutManager(this@HistoryActivity)
        rvHistory.adapter = AdapterItemHistory(generateDummyHistoryData(), this@HistoryActivity)
    }

    private fun generateDummyHistoryData(): List<EntityHistory> {
        val dummyData = mutableListOf<EntityHistory>()
        for (i in 1..10) {
            dummyData.add(
                EntityHistory(
                    id = i,
                    image = R.drawable.gallery_icon.toString(),
                    desc = "Description for item $i",
                    date = Date().toString()
                )
            )
        }
        return dummyData
    }
}