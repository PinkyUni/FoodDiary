package com.pinkyuni.entities.food

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.entities.associative.FoodCategory
import com.pinkyuni.entities.core.Category
import com.pinkyuni.entities.core.Food

data class FoodCategories(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "food",
        entityColumn = "category",
        associateBy = Junction(FoodCategory::class)
    )
    val categories: List<Category>
)