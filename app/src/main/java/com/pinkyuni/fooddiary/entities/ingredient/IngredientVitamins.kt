package com.pinkyuni.fooddiary.entities.ingredient

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.fooddiary.entities.associative.IngredientVitaminCrossRef
import com.pinkyuni.fooddiary.entities.core.Ingredient
import com.pinkyuni.fooddiary.entities.core.Vitamin

data class IngredientVitamins(
    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "id",
        entity = Vitamin::class,
        entityColumn = "id",
        associateBy = Junction(
            IngredientVitaminCrossRef::class,
            parentColumn = "ingredient_id",
            entityColumn = "vitamin_id"
        )
    )
    val vitamins: List<Vitamin>
)