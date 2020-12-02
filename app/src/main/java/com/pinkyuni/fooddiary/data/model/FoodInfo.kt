package com.pinkyuni.fooddiary.data.model

import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Ingredient
import com.pinkyuni.fooddiary.entities.core.Vitamin

data class FoodInfo(
    val food: Food,
    val ingredients: List<Ingredient>,
    val vitamins: List<Vitamin>
)

