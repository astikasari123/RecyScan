package com.capstone.recyscan.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capstone.recyscan.data.local.entity.EntityHistory

@Dao
interface DaoHistory {
    @Query("SELECT * FROM EntityHistory")
    fun getAllHistory(): LiveData<List<EntityHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(entityHistory: EntityHistory)

    @Query("DELETE FROM EntityHistory WHERE EntityHistory.id = :id")
    suspend fun deleteHistory(id: String) : Int

}