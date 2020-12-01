package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("name")]
)
data class Vitamin(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)