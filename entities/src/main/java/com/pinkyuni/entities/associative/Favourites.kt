package com.pinkyuni.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    primaryKeys = ["user_id", "food_id"]
)
data class Favourites(
    @ColumnInfo(name = "user_id")
    val user: Int,
    @ColumnInfo(name = "food_id")
    val food: Int
)