package com.capstone.recyscan.di

import android.content.Context
import com.capstone.recyscan.data.local.room.DatabaseHistory
import com.capstone.recyscan.repository.RepositoryHistory

object Injection {
    fun getRepositoryHistory (context: Context) : RepositoryHistory = RepositoryHistory.getInstanceOfRepositoryHistory(
        DatabaseHistory.getInstanceOfDatabaseHistory(context).daoHistory()
    )
}