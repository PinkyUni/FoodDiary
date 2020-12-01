package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    @PrimaryKey
    val id: Long,
    val name: String,
    val percentage: Int
)