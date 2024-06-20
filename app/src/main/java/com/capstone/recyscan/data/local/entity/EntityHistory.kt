package com.capstone.recyscan.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityHistory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "scan_result")
    val scanResult: String,

    @ColumnInfo(name = "count_result")
    val countResult: String,

    @ColumnInfo(name = "recommend_result")
    val recommendResult: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "date")
    val date: String,
)
