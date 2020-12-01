package com.pinkyuni.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientInfo(
    @PrimaryKey
    val id: Int,
    val calories: Int,
    val protein: Float,
    val fat: Float,
    val carbohydrate: Float,
    @ColumnInfo(name = "unit_id")
    val unit: Int
)