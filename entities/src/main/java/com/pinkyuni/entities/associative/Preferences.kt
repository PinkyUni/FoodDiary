package com.pinkyuni.entities.associative

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["user", "category"]
)
data class Preferences(
    val user: Long,
    val category: Long
)