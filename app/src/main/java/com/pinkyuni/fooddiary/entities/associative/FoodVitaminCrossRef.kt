package com.pinkyuni.fooddiary.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Vitamin

@Entity(
    tableName = "Food_vitamin",
    primaryKeys = ["food_id", "vitamin_id"],
    indices = [
        Index("food_id"),
        Index("vitamin_id")
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
            entity = Vitamin::class,
            parentColumns = ["id"],
            childColumns = ["vitamin_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class FoodVitaminCrossRef(
    @ColumnInfo(name = "food_id")
    val food: Int,
    @ColumnInfo(name = "vitamin_id")
    val vitamin: Int
)