package com.pinkyuni.fooddiary.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.pinkyuni.fooddiary.entities.associative.HistoryFoodCrossRef
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Unit

data class HistoryRecords(
    @Embedded val history: History,
    @Relation(
        parentColumn = "id",
        entityColumn = "history_id",
        entity = HistoryFoodCrossRef::class
    )
    val foodList: List<FoodHistoryAndFoodAndUnit>
)

data class FoodHistoryAndFoodAndUnit(
    @Embedded
    val foodHistory: HistoryFoodCrossRef,
    @Relation(
        parentColumn = "food_id",
        entityColumn = "id"
    )
    val food: Food,
    @Relation(
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unit: Unit
)
