package com.pinkyuni.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.entities.core.Ingredient
import com.pinkyuni.entities.core.Vitamin

@Entity(
    tableName = "Ingredient_vitamin",
    primaryKeys = ["ingredient_id", "vitamin_id"],
    indices = [Index("ingredient_id"), Index("vitamin_id")],
    foreignKeys = [
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Vitamin::class,
            parentColumns = ["id"],
            childColumns = ["vitamin_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class IngredientVitaminCrossRef(
    @ColumnInfo(name = "ingredient_id")
    val ingredient: Int,
    @ColumnInfo(name = "vitamin_id")
    val vitamin: Int
)