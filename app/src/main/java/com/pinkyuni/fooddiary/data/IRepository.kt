package com.pinkyuni.fooddiary.data

import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.entities.core.Food
import io.reactivex.Completable
import io.reactivex.Single

interface IRepository {

    fun addFood(food: Food): Single<Long>

    fun updateFood(food: Food): Completable

    fun deleteFood(food: Food): Completable

    fun getFoodInfo(foodId: Long): Single<FoodInfo?>

}