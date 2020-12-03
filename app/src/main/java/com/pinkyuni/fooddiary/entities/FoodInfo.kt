package com.pinkyuni.fooddiary.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Unit

@Entity(
    tableName = "Food_info",
    primaryKeys = ["food_id", "unit_id"],
    indices = [
        Index("unit_id", "food_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class FoodInfo(
    @ColumnInfo(name = "food_id")
    val food: Long,
    val calories: Long,
    @ColumnInfo(name = "unit_id")
    val unit: Long
)