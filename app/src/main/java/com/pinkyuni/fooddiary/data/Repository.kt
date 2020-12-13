package com.pinkyuni.fooddiary.data

import android.content.Context
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.mapToMealHistory
import com.pinkyuni.fooddiary.usecases.FoodDao
import com.pinkyuni.fooddiary.usecases.HistoryDao

class Repository(context: Context) : IRepository {

    private lateinit var foodDao: FoodDao
    private lateinit var historyDao: HistoryDao

    init {
        try {
            val database = DiaryDatabase.getAppDataBase(context = context)
            foodDao = database.foodDao()
            historyDao = database.historyDao()
        } catch (e: Exception) {
            println("Error happened while creating database :/")
        }
    }

    override fun addFood(food: Food) = foodDao.insert(food)

    override fun updateFood(food: Food) = foodDao.update(food)

    override fun deleteFood(food: Food) = foodDao.delete(food)

    override fun getFoodInfo(foodId: Long) = foodDao.getFoodInfo(foodId)

    override fun getHistoryForDay(day: Long, user: Long) =
        historyDao.getHistoryForDay(day, user).map { it.mapToMealHistory() }

}