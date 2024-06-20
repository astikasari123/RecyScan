package com.capstone.recyscan.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.recyscan.di.Injection
import com.capstone.recyscan.repository.RepositoryHistory
import com.capstone.recyscan.viewmodel.HistoryViewModel

@Suppress("UNCHECKED_CAST")
class HistoryViewModelFactory(private val repositoryHistory: RepositoryHistory) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(repositoryHistory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: HistoryViewModelFactory? = null

        fun getInstanceOfHistoryViewModelFactory(context: Context): HistoryViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: HistoryViewModelFactory(
                    Injection.getRepositoryHistory(context)
                )
            }.also { instance = it }
    }

}