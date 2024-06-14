package com.capstone.recyscan.repository

import androidx.lifecycle.LiveData
import com.capstone.recyscan.data.local.entity.EntityHistory
import com.capstone.recyscan.data.local.room.DaoHistory

class RepositoryHistory private constructor(
    private val daoHistory: DaoHistory
) {
    fun getAllHistory(): LiveData<List<EntityHistory>> {
        return daoHistory.getAllHistory()
    }

    suspend fun insertHistory(entityHistory: EntityHistory) {
        return daoHistory.insertHistory(entityHistory)
    }

    companion object {
        @Volatile
        private var instance: RepositoryHistory? = null

        fun getInstanceOfRepositoryHistory(
            daoHistory: DaoHistory
        ): RepositoryHistory = instance ?: synchronized(this) {
            instance ?: RepositoryHistory(daoHistory).also { instance = it }
        }
    }
}
