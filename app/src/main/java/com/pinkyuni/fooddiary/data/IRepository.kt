package com.pinkyuni.fooddiary.data

import com.pinkyuni.fooddiary.entities.MealHistory
import com.pinkyuni.fooddiary.entities.core.Activity
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Gender
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.User
import com.pinkyuni.fooddiary.entities.food.FoodAllInfo
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

    fun getHistoryForDay(day: Long, user: Long): Single<List<MealHistory>>

    fun getActivities(): Single<List<Activity>>

    fun getGenders(): Single<List<Gender>>

    fun getTargets(): Single<List<Target>>
}