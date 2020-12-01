package com.pinkyuni.entities

import androidx.room.*
import com.pinkyuni.entities.core.User
import java.sql.Time
import java.util.*

@Entity(
    indices = [Index("record_date"), Index("user_id")],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"], childColumns = ["user_id"]
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
    val recordTime: Time
)