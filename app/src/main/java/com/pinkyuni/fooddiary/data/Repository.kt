package com.pinkyuni.fooddiary.data

import android.content.Context
import com.pinkyuni.fooddiary.entities.core.Activity
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Gender
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.User
import com.pinkyuni.fooddiary.entities.mapToMealHistory
import com.pinkyuni.fooddiary.usecases.*
import io.reactivex.Completable
import io.reactivex.Single

class Repository(context: Context) : IRepository {

    private lateinit var foodDao: FoodDao
    private lateinit var historyDao: HistoryDao
    private lateinit var userDao: UserDao
    private lateinit var activityDao: ActivityDao
    private lateinit var genderDao: GenderDao
    private lateinit var targetDao: TargetDao

    init {
        try {
            val database = DiaryDatabase.getAppDataBase(context = context)
            foodDao = database.foodDao()
            historyDao = database.historyDao()
            userDao = database.userDao()
            activityDao = database.activityDao()
            targetDao = database.targetDao()
            genderDao = database.genderDao()
        } catch (e: Exception) {
            println("Error happened while creating database :/")
        }
    }

    override fun getUser(login: String, password: String): Single<User> = userDao.getUser(login, password)

    override fun getUser(id: Long): Single<User> = userDao.getUserById(id)

    override fun saveUser(user: User): Completable = userDao.update(user)

    override fun addFood(food: Food) = foodDao.insert(food)

    override fun updateFood(food: Food) = foodDao.update(food)

    override fun deleteFood(food: Food) = foodDao.delete(food)

    override fun getFoodInfo(foodId: Long) = foodDao.getFoodInfo(foodId)

    override fun getHistoryForDay(day: Long, user: Long) =
        historyDao.getHistoryForDay(day, user).map { it.mapToMealHistory() }

    override fun getActivities(): Single<List<Activity>> = activityDao.getActivities()

    override fun getGenders(): Single<List<Gender>> = genderDao.getGenders()

    override fun getTargets(): Single<List<Target>> = targetDao.getTargets()

}