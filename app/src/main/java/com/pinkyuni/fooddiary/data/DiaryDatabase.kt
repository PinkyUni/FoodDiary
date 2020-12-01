package com.pinkyuni.fooddiary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pinkyuni.entities.*
import com.pinkyuni.entities.associative.*
import com.pinkyuni.entities.core.*
import com.pinkyuni.entities.core.Target
import com.pinkyuni.entities.core.Unit
import com.pinkyuni.fooddiary.utils.DateTypeConverter
import com.pinkyuni.usecases.FoodDao
import com.pinkyuni.usecases.GenderDao
import com.pinkyuni.usecases.UserDao

@Database(
    entities = [User::class, Gender::class, Target::class, Activity::class,
        Category::class, Food::class, Favourites::class, FoodCategory::class,
        Vitamin::class, FoodVitaminCrossRef::class, Meal::class, Monitoring::class,
        Ingredient::class, IngredientVitaminCrossRef::class, Unit::class,
        FoodInfo::class, History::class, HistoryFood::class, IngredientInfo::class,
        Preferences::class, WeightStatistics::class, StateStatistics::class],
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun genderDao(): GenderDao
    abstract fun foodDao(): FoodDao

    companion object {
        var INSTANCE: DiaryDatabase? = null

        fun getAppDataBase(context: Context): DiaryDatabase? {
            if (INSTANCE == null) {
                synchronized(DiaryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDatabase::class.java,
                        "FoodDiary"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}