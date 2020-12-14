package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Meal
import io.reactivex.Single

@Dao
interface MealDao {

    @Query("SELECT * FROM Meal")
    fun getMeals(): Single<List<Meal>>

}