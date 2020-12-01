package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("name", unique = true)]
)
data class Ingredient(
    @PrimaryKey
    val id: Int,
    val name: String
)