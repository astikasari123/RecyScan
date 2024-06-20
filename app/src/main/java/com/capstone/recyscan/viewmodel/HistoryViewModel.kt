package com.capstone.recyscan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.repository.RepositoryHistory
import kotlinx.coroutines.launch

class HistoryViewModel(private val repositoryHistory: RepositoryHistory) : ViewModel() {
    fun getAllHistory() = repositoryHistory.getAllHistory()
    fun insertHistory(entityHistory: EntityHistory) {
        viewModelScope.launch {
            repositoryHistory.insertHistory(entityHistory)
        }
    }

    fun deleteHistory(historyId: String) {
        viewModelScope.launch {
            repositoryHistory.deleteHistory(historyId)
        }
    }
}