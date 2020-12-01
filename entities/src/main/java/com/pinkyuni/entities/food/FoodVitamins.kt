package com.pinkyuni.entities.food

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.entities.associative.FoodVitaminCrosRef
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Vitamin

data class FoodVitamins(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "id",
        entity = Vitamin::class,
        entityColumn = "id",
        associateBy = Junction(
            FoodVitaminCrosRef::class,
            parentColumn = "foodId",
            entityColumn = "vitaminId"
        )
    )
    val vitamins: List<Vitamin>
)