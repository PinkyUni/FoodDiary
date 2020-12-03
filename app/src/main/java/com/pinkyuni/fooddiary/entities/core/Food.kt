package com.pinkyuni.fooddiary.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index("name")
    ]
)
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val image: String? = null
)