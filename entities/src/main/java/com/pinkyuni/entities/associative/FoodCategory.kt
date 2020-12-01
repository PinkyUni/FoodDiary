package com.pinkyuni.entities.associative

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pinkyuni.entities.core.Category
import com.pinkyuni.entities.core.Food

@Entity(
    primaryKeys = ["food", "category"]
)
data class FoodCategory(
    val food: Long,
    val category: Long
)