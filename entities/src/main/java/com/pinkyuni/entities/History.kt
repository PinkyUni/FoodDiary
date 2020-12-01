package com.pinkyuni.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pinkyuni.entities.core.Meal
import com.pinkyuni.entities.core.User
import java.sql.Time
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"], childColumns = ["user_id"]
        ),
        ForeignKey(
            entity = Meal::class,
            parentColumns = ["id"], childColumns = ["meal_id"]
        )
    ]
)
data class History(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "record_date")
    val recordDate: Date,
    @ColumnInfo(name = "record_time")
    val recordTime: Time,
    @ColumnInfo(name = "user_id")
    val user: Int,
    @ColumnInfo(name = "meal_id")
    val meal: Int
)