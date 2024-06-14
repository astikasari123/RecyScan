package com.capstone.recyscan.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.recyscan.data.local.entity.EntityHistory

@Database(entities = [EntityHistory :: class], version = 1, exportSchema = false)
abstract class DatabaseHistory : RoomDatabase(){
    abstract fun daoHistory() : DaoHistory

    companion object {
        @Volatile
        private var instance: DatabaseHistory? = null
        fun getInstanceOfDatabaseHistory(context: Context): DatabaseHistory =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHistory::class.java,
                    "History.db"
                ).build()
               }
       }

}