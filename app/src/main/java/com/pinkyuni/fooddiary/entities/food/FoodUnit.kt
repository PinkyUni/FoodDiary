package com.pinkyuni.fooddiary.entities.food

import androidx.room.Embedded
import androidx.room.Relation
import com.pinkyuni.fooddiary.entities.FoodInfo
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Unit

data class FoodUnit(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "id",
        entityColumn = "food_id",
        entity = FoodInfo::class
    )
    val foodInfo: List<FoodUnitName>
)

data class FoodUnitName(
    @Embedded val foodInfo: FoodInfo,
    @Relation(
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unit: Unit
)