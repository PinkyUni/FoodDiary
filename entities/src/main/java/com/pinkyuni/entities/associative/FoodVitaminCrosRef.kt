package com.pinkyuni.entities.associative

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Vitamin

@Entity(
    primaryKeys = ["foodId", "vitaminId"],
    indices = [Index("foodId"), Index("vitaminId")],
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["foodId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Vitamin::class,
            parentColumns = ["id"],
            childColumns = ["vitaminId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FoodVitaminCrosRef(
    val foodId: Long,
    val vitaminId: Long
)