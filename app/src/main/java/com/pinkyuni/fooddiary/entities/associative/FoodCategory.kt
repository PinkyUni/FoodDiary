package com.pinkyuni.entities.associative

import androidx.room.*

@Entity(
    tableName = "Food_category",
    primaryKeys = ["food_id", "category_id"]
)
data class FoodCategory(
    @ColumnInfo(name = "food_id")
    val food: Int,
    @ColumnInfo(name = "category_id")
    val category: Int
)