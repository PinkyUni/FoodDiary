package com.pinkyuni.fooddiary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pinkyuni.fooddiary.entities.*
import com.pinkyuni.fooddiary.entities.associative.*
import com.pinkyuni.fooddiary.entities.core.*
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.Unit
import com.pinkyuni.fooddiary.usecases.*
import com.pinkyuni.fooddiary.utils.DateTypeConverter

@Database(
    entities = [User::class, Gender::class, Target::class, Activity::class,
        Category::class, Food::class, Favourites::class, FoodCategory::class,
        Vitamin::class, FoodVitaminCrossRef::class, Meal::class, Monitoring::class,
        Ingredient::class, IngredientVitaminCrossRef::class, Unit::class,
        FoodInfo::class, History::class, HistoryFood::class, IngredientInfo::class,
        Preferences::class, WeightStatistics::class, StateStatistics::class,
        FoodIngredientCrossRef::class],
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
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DiaryDatabase::class.java,
                            "FoodDiary"
                        )
                            .createFromAsset("diary.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}