package com.pinkyuni.fooddiary.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.pinkyuni.fooddiary.entities.associative.HistoryFoodCrossRef
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Meal
import com.pinkyuni.fooddiary.entities.core.Unit
import java.util.*

typealias DayHistoryRecords = List<HistoryRecords>
fun DayHistoryRecords.mapToMealHistory() =
    map { it.toMealHistory() }

data class HistoryRecords(
    @Embedded val history: History,
    @Relation(
        parentColumn = "meal_id",
        entityColumn = "id"
    )
    val meal: Meal,
    @Relation(
        parentColumn = "id",
        entityColumn = "history_id",
        entity = HistoryFoodCrossRef::class
    )
    val foodList: List<FoodHistoryAndFoodAndUnit>
) {

    fun toMealHistory() =
        MealHistory(
            meal,
            history.recordDate,
            history.recordTime,
            foodList.mapToFoodHistory()
        )

}

data class MealHistory(
    val meal: Meal,
    val date: Date,
    val time: String,
    val foodList: List<FoodHistory>
)

typealias FoodHistoryAndFoodAndUnitList = List<FoodHistoryAndFoodAndUnit>
fun FoodHistoryAndFoodAndUnitList.mapToFoodHistory() = map { it.toFoodHistory() }

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
) {

    fun toFoodHistory() =
        FoodHistory(
            food,
            foodHistory.size,
            unit
        )

}

data class FoodHistory(
    val food: Food,
    val size: Long,
    val unit: Unit
)