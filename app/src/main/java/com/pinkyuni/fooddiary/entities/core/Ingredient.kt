package com.pinkyuni.fooddiary.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index("name", unique = true)
    ]
)
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)