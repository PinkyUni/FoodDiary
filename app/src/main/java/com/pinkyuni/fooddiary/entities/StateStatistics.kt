package com.pinkyuni.fooddiary.entities

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Monitoring
import com.pinkyuni.fooddiary.entities.core.User
import java.util.*

@Entity(
    indices = [
        Index("user_id", "record_date"),
        Index("user_id", "monitoring_id")
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
            entity = Monitoring::class,
            parentColumns = ["id"],
            childColumns = ["monitoring_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class StateStatistics(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "user_id")
    val user: Long,
    @ColumnInfo(name = "monitoring_id")
    val monitoring: Long,
    val description: String,
    @ColumnInfo(name = "record_date")
    val recordDate: Date,
    @ColumnInfo(name = "record_time")
    val recordTime: String
)