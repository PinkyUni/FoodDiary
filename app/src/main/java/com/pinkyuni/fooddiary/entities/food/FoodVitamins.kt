package com.pinkyuni.entities.food

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.entities.associative.FoodVitaminCrossRef
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Vitamin

data class FoodVitamins(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "id",
        entity = Vitamin::class,
        entityColumn = "id",
        associateBy = Junction(
            FoodVitaminCrossRef::class,
            parentColumn = "food_id",
            entityColumn = "vitamin_id"
        )
    )
    val vitamins: List<Vitamin>
)