package com.pinkyuni.entities.associative

import androidx.room.Entity

@Entity(
    primaryKeys = ["user", "food"]
)
data class Favourites(
    val user: Long,
    val food: Long
)