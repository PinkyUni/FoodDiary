package com.pinkyuni.fooddiary.data

import com.pinkyuni.fooddiary.data.model.DayInfo
import com.pinkyuni.fooddiary.data.model.FoodRecord
import com.pinkyuni.fooddiary.entities.MealHistory
import com.pinkyuni.fooddiary.entities.core.*
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.Unit
import com.pinkyuni.fooddiary.entities.food.FoodAllInfo
import com.pinkyuni.fooddiary.entities.food.FoodIngredients
import com.pinkyuni.fooddiary.entities.food.FoodUnit
import io.reactivex.Completable
import io.reactivex.Single

interface IRepository {

    fun getUser(login: String, password: String): Single<User>

    fun getUser(id: Long): Single<User>

    fun saveUser(user: User): Completable

    fun addFood(food: Food): Single<Long>

    fun updateFood(food: Food): Completable

    fun deleteFood(food: Food): Completable

    fun getFoodInfo(foodId: Long): Single<FoodAllInfo>

    fun getHistoryForDay(day: Long): Single<List<MealHistory>>

    fun getActivities(): Single<List<Activity>>

    fun getGenders(): Single<List<Gender>>

    fun getTargets(): Single<List<Target>>

    fun getUnits(): Single<List<Unit>>

    fun getMeals(): Single<List<Meal>>

    fun addFoodRecord(foodRecord: FoodRecord): Single<Long>

    fun getFoodList(): Single<List<Food>>

    fun getFoodUnits(foodId: Long): Single<FoodUnit>

    fun getFoodIngredients(foodId: Long): Single<List<FoodIngredients>>

    fun getDayCalories(day: Long): Single<DayInfo>

}