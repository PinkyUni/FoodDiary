package com.pinkyuni.fooddiary.entities.associative

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Category
import com.pinkyuni.fooddiary.entities.core.Food

@Entity(
    tableName = "Food_category",
    primaryKeys = ["food_id", "category_id"],
    indices = [
        Index("food_id"),
        Index("category_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class FoodCategory(
    @ColumnInfo(name = "category_id")
    val category: Long,
    @ColumnInfo(name = "food_id")
    val food: Long
)