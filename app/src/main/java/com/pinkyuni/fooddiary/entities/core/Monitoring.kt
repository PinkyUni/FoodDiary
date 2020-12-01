package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Monitoring(
    @PrimaryKey
    val id: Long,
    val name: String
)