package com.pinkyuni.fooddiary.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index("name", unique = true)
    ]
)
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val percentage: Long
)

const val BREAKFAST = 1L
const val LUNCH = 2L
const val DINNER = 3L
const val SNACK = 4L