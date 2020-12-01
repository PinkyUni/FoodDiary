package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("name")]
)
data class Activity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String
)