package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("name")]
)
data class Target(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)