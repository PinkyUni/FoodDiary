package com.pinkyuni.fooddiary.entities

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Meal
import com.pinkyuni.fooddiary.entities.core.User
import java.util.*

@Entity(
    indices = [
        Index("user_id", "meal_id"),
        Index("user_id", "record_date")
    ],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Meal::class,
            parentColumns = ["id"],
            childColumns = ["meal_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "record_date")
    val recordDate: Date,
    @ColumnInfo(name = "record_time")
    val recordTime: String,
    @ColumnInfo(name = "user_id")
    val user: Int,
    @ColumnInfo(name = "meal_id")
    val meal: Int
)