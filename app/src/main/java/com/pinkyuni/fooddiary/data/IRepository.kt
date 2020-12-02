package com.pinkyuni.fooddiary.data

import com.pinkyuni.fooddiary.data.model.FoodInfo

interface IRepository {

    fun getFoodInfo(foodId: Int): FoodInfo?

}