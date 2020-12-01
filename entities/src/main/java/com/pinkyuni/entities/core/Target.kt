package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("name", unique = true)]
)
data class Target(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)