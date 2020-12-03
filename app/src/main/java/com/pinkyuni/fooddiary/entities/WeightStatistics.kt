package com.pinkyuni.fooddiary.entities

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.User
import java.util.*

@Entity(
    indices = [
        Index("user_id", "record_date"),
    ],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"], childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class WeightStatistics(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "user_id")
    val user: Long,
    val weight: Float,
    @ColumnInfo(name = "record_date")
    val recordDate: Date,
    @ColumnInfo(name = "record_time")
    val recordTime: String
)