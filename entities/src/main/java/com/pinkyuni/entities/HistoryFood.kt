package com.pinkyuni.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Unit

@Entity(
    tableName = "History_food",
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"], childColumns = ["food_id"]
        ),
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"], childColumns = ["unit_id"]
        )
    ]
)
data class HistoryFood(
    @PrimaryKey
    @ColumnInfo(name = "history_id")
    val id: Int,
    @ColumnInfo(name = "food_id")
    val food: Int,
    val size: Int,
    @ColumnInfo(name = "unit_id")
    val unit: Int
)