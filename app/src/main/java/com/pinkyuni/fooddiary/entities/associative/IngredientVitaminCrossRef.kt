package com.pinkyuni.fooddiary.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.fooddiary.entities.core.Ingredient
import com.pinkyuni.fooddiary.entities.core.Vitamin

@Entity(
    tableName = "Ingredient_vitamin",
    primaryKeys = ["ingredient_id", "vitamin_id"],
    indices = [
        Index("ingredient_id"),
        Index("vitamin_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Vitamin::class,
            parentColumns = ["id"],
            childColumns = ["vitamin_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class IngredientVitaminCrossRef(
    @ColumnInfo(name = "ingredient_id")
    val ingredient: Int,
    @ColumnInfo(name = "vitamin_id")
    val vitamin: Int
)