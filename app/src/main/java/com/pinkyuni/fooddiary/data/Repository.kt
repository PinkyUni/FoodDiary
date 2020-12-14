package com.pinkyuni.fooddiary.data

import android.content.Context
import android.content.SharedPreferences
import com.pinkyuni.fooddiary.data.model.FoodRecord
import com.pinkyuni.fooddiary.entities.History
import com.pinkyuni.fooddiary.entities.associative.HistoryFoodCrossRef
import com.pinkyuni.fooddiary.entities.core.Activity
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Gender
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.Unit
import com.pinkyuni.fooddiary.entities.core.User
import com.pinkyuni.fooddiary.entities.mapToMealHistory
import com.pinkyuni.fooddiary.usecases.*
import com.pinkyuni.fooddiary.utils.formatTime
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class Repository(context: Context) : IRepository {

    companion object {
        const val SP_TAG = "shared_prefs"
        const val USER_ID_TAG = "user_id"
    }

    private val sharedPreferences: SharedPreferences
    private lateinit var foodDao: FoodDao
    private lateinit var historyDao: HistoryDao
    private lateinit var userDao: UserDao
    private lateinit var activityDao: ActivityDao
    private lateinit var genderDao: GenderDao
    private lateinit var targetDao: TargetDao
    private lateinit var unitDao: UnitDao

    init {
        sharedPreferences = context.getSharedPreferences(SP_TAG, Context.MODE_PRIVATE)
        try {
            val database = DiaryDatabase.getAppDataBase(context = context)
            foodDao = database.foodDao()
            historyDao = database.historyDao()
            userDao = database.userDao()
            activityDao = database.activityDao()
            targetDao = database.targetDao()
            genderDao = database.genderDao()
            unitDao = database.unitDao()
        } catch (e: Exception) {
            println("Error happened while creating database :/")
        }
    }

    private fun getUserLocalId(): Long {
        return sharedPreferences.getLong(USER_ID_TAG, 0)
    }

    override fun getUser(login: String, password: String): Single<User> =
        userDao.getUser(login, password)

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

    override fun getUnits(): Single<List<Unit>> = unitDao.getUnits()

    override fun addFoodRecord(foodRecord: FoodRecord): Completable {
        val curDate = Date()
        val time = curDate.formatTime()
        return Completable.fromCallable {
            val historyId = historyDao.getOrInsertHistory(
                History(
                    0,
                    curDate,
                    time,
                    getUserLocalId(),
                    foodRecord.meal
                )
            )
            historyDao.insertFoodRecord(
                HistoryFoodCrossRef(
                    history = historyId,
                    food = foodRecord.food,
                    size = foodRecord.size,
                    unit = foodRecord.unit
                )
            )
        }
    }

    override fun getFoodList(): Single<List<Food>> = foodDao.getFoodList()

}