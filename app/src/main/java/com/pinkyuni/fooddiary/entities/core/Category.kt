package com.pinkyuni.fooddiary.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index("name", unique = true)
    ]
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)