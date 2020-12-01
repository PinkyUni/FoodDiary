package com.pinkyuni.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    primaryKeys = ["user_id", "category_id"]
)
data class Preferences(
    @ColumnInfo(name = "user_id")
    val user: Long,
    @ColumnInfo(name = "category_id")
    val category: Long
)