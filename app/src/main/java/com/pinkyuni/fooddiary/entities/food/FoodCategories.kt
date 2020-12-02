package com.pinkyuni.fooddiary.entities.food

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.fooddiary.entities.associative.FoodCategory
import com.pinkyuni.fooddiary.entities.core.Category
import com.pinkyuni.fooddiary.entities.core.Food

data class FoodCategories(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "food_id",
        entityColumn = "category_id",
        associateBy = Junction(FoodCategory::class)
    )
    val categories: List<Category>
)