package com.capstone.recyscan.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.recyscan.R
import androidx.activity.viewModels
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.databinding.ActivityHistoryBinding
import com.capstone.recyscan.viewmodel.HistoryViewModel
import com.capstone.recyscan.viewmodelfactory.HistoryViewModelFactory
import com.capstone.recyscan.views.adapters.AdapterItemHistory

class HistoryActivity : AppCompatActivity(), AdapterItemHistory.HistoryAction {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapterHistory: AdapterItemHistory
    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory.getInstanceOfHistoryViewModelFactory(this@HistoryActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setupRecyclerView()
            setupToolBar()
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
        adapterHistory = AdapterItemHistory(this@HistoryActivity)
        rvHistory.layoutManager = LinearLayoutManager(this@HistoryActivity)
        rvHistory.adapter = adapterHistory
        historyViewModel.apply {
            getAllHistory().observe(this@HistoryActivity) { valueListHistory ->
                if (valueListHistory.isNullOrEmpty()) {
                    rvHistory.visibility = View.GONE
                    emptyHistoryLayout.root.visibility = View.VISIBLE
                } else {
                    emptyHistoryLayout.root.visibility = View.GONE
                    rvHistory.visibility = View.VISIBLE
                    adapterHistory.submitList(valueListHistory.reversed())
                }
            }
        }
    }

    override fun onDeleteClicked(history: EntityHistory) {
        historyViewModel.deleteHistory(history.id.toString())
    }
}
