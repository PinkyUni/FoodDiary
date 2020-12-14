package com.pinkyuni.fooddiary.entities

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Ingredient
import com.pinkyuni.fooddiary.entities.core.Unit

@Entity(
    tableName = "Ingredient_info",
    primaryKeys = ["ingredient_id", "unit_id"],
    indices = [
        Index("ingredient_id", "unit_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Unit::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class IngredientInfo(
    @ColumnInfo(name = "ingredient_id")
    val ingredient: Long,
    val calories: Long,
    val protein: Float,
    val fat: Float,
    val carbohydrate: Float,
    @ColumnInfo(name = "unit_id")
    val unit: Long
)