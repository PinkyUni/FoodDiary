package com.pinkyuni.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Unit

@Entity(
    tableName = "Food_info",
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"], childColumns = ["food"]
        ),
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"], childColumns = ["unit"]
        )
    ]
)
data class FoodInfo(
    @ColumnInfo(name = "food_id")
    val food: Int,
    val calories: Int,
    @ColumnInfo(name = "unit_id")
    val unit: Int
)