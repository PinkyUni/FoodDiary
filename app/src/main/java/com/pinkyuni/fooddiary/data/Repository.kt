package com.pinkyuni.fooddiary.data

import android.content.Context
import com.pinkyuni.fooddiary.entities.food.map

class Repository(private val context: Context) : IRepository {

    private lateinit var database: DiaryDatabase

    init {
        try {
            database = DiaryDatabase.getAppDataBase(context = context)
        } catch (e: Exception) {
            println("Error happened while creating database :/")
        }
    }

    override fun getFoodInfo(foodId: Int) =
        database.foodDao().getFoodInfo(foodId).map()

}