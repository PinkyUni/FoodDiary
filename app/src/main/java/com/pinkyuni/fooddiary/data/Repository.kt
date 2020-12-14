package com.pinkyuni.fooddiary.data

import android.content.Context
import android.content.SharedPreferences
import com.pinkyuni.fooddiary.data.model.FoodRecord
import com.pinkyuni.fooddiary.entities.History
import com.pinkyuni.fooddiary.entities.associative.HistoryFoodCrossRef
import com.pinkyuni.fooddiary.entities.core.*
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.Unit
import com.pinkyuni.fooddiary.entities.food.FoodUnit
import com.pinkyuni.fooddiary.entities.mapToMealHistory
import com.pinkyuni.fooddiary.usecases.*
import com.pinkyuni.fooddiary.utils.formatTime
import com.pinkyuni.fooddiary.utils.getDateMillis
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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
    private lateinit var mealDao: MealDao

    init {
        sharedPreferences = context.getSharedPreferences(SP_TAG, Context.MODE_PRIVATE)
        sharedPreferences.edit().putLong(USER_ID_TAG, 10).apply()
        try {
            val database = DiaryDatabase.getAppDataBase(context = context)
            foodDao = database.foodDao()
            historyDao = database.historyDao()
            userDao = database.userDao()
            activityDao = database.activityDao()
            targetDao = database.targetDao()
            genderDao = database.genderDao()
            unitDao = database.unitDao()
            mealDao = database.mealDao()
        } catch (e: Exception) {
            println("Error happened while creating database :/")
        }
    }

    private fun getUserLocalId(): Long {
        return sharedPreferences.getLong(USER_ID_TAG, 10)
    }

    override fun getUser(login: String, password: String): Single<User> =
        userDao.getUser(login, password)

    override fun getUser(id: Long): Single<User> = userDao.getUserById(id)

    override fun saveUser(user: User): Completable = userDao.update(user)

    override fun addFood(food: Food) = foodDao.insert(food)

    override fun updateFood(food: Food) = foodDao.update(food)

    override fun deleteFood(food: Food) = foodDao.delete(food)

    override fun getFoodInfo(foodId: Long) = foodDao.getFoodInfo(foodId)

    override fun getHistoryForDay(day: Long) =
        historyDao.getHistoryForDay(day, getUserLocalId()).map { it.mapToMealHistory() }

    override fun getActivities(): Single<List<Activity>> = activityDao.getActivities()

    override fun getGenders(): Single<List<Gender>> = genderDao.getGenders()

    override fun getTargets(): Single<List<Target>> = targetDao.getTargets()

    override fun getUnits(): Single<List<Unit>> = unitDao.getUnits()

    override fun getMeals(): Single<List<Meal>> = mealDao.getMeals()

    override fun addFoodRecord(foodRecord: FoodRecord): Single<Long> {
        return Single.fromCallable {
            val time = Date().formatTime()
            val historyId = historyDao.getOrInsertHistory(
                History(
                    0,
                    Date(getDateMillis()),
                    time,
                    getUserLocalId(),
                    foodRecord.meal
                )
            )
            historyId
        }.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                historyDao.insertFoodRecord(
                    HistoryFoodCrossRef(
                        history = it,
                        food = foodRecord.food,
                        size = foodRecord.size,
                        unit = foodRecord.unit
                    )
                )
            }
    }

    override fun getFoodList(): Single<List<Food>> = foodDao.getFoodList()

    override fun getFoodUnits(foodId: Long): Single<FoodUnit> = foodDao.getFoodUnits(foodId)

}