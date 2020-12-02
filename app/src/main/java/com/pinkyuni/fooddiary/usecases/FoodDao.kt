package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.associative.FoodVitaminCrossRef
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Vitamin
import com.pinkyuni.fooddiary.entities.food.FoodVitamins

@Dao
interface FoodDao {

    @Insert
    fun insert(vitamin: Vitamin)

    @Insert
    fun insert(food: Food)

    @Transaction
    @Query("SELECT * FROM Food")
    fun getFoodVitamins(): List<FoodVitamins>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFoodVitamins(foodVitamins: List<FoodVitaminCrossRef>)

}