package com.pinkyuni.fooddiary.entities.associative

import androidx.room.*
import com.pinkyuni.fooddiary.entities.History
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Unit


@Entity(
    tableName = "History_food",
    primaryKeys = ["history_id", "food_id", "unit_id"],
    indices = [
        Index("food_id"),
        Index("history_id"),
        Index("unit_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = History::class,
            parentColumns = ["id"],
            childColumns = ["history_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class HistoryFoodCrossRef(
    @ColumnInfo(name = "history_id")
    val history: Long = 0,
    @ColumnInfo(name = "food_id")
    val food: Long,
    val size: Long,
    @ColumnInfo(name = "unit_id")
    val unit: Long
)