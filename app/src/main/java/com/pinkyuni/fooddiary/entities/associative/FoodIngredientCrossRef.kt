package com.pinkyuni.fooddiary.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Ingredient

@Entity(
    tableName = "Food_ingredient",
    primaryKeys = ["food_id", "ingredient_id"],
    indices = [
        Index("food_id"),
        Index("ingredient_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class FoodIngredientCrossRef(
    @ColumnInfo(name = "food_id")
    val food: Long,
    @ColumnInfo(name = "ingredient_id")
    val ingredient: Long
)