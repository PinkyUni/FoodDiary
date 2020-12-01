package com.pinkyuni.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pinkyuni.entities.core.Monitoring
import com.pinkyuni.entities.core.User
import java.sql.Time
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        ),
    ForeignKey(
        entity = Monitoring::class,
        parentColumns = ["id"],
        childColumns = ["monitoring_id"]
    )
    ]
)
data class StateStatistics(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "user_id")
    val user: Long,
    @ColumnInfo(name = "monitoring_id")
    val monitoring: Long,
    val description: String,
    @ColumnInfo(name = "record_date")
    val recordDate: Date,
    @ColumnInfo(name = "record_time")
    val recordTime: Time
)