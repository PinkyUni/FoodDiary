package com.pinkyuni.fooddiary.entities.food

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.fooddiary.entities.associative.FoodIngredientCrossRef
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Ingredient

data class FoodIngredients(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "id",
        entity = Ingredient::class,
        entityColumn = "id",
        associateBy = Junction(
            FoodIngredientCrossRef::class,
            parentColumn = "food_id",
            entityColumn = "ingredient_id"
        )
    )
    val ingredients: List<Ingredient>
)