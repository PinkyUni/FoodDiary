package com.pinkyuni.usecases

import androidx.room.*
import com.pinkyuni.entities.associative.FoodVitaminCrosRef
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.Vitamin
import com.pinkyuni.entities.food.FoodVitamins

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
    fun addFoodVitamins(foodVitamins: List<FoodVitaminCrosRef>)

}