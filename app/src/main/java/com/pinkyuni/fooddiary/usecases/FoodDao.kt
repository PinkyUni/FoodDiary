package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.associative.FoodVitaminCrossRef
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Vitamin
import com.pinkyuni.fooddiary.entities.food.FoodAllInfo
import com.pinkyuni.fooddiary.entities.food.FoodIngredients
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

    @Transaction
    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodVitamins(foodId: Int): List<FoodVitamins>

    @Transaction
    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodIngredients(foodId: Int): List<FoodIngredients>

    @Query("SELECT * FROM FOOD WHERE name LIKE  '%' || :name || '%'")
    fun getFood(name: String): List<Food>

    @Transaction
    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodInfo(foodId: Int): FoodAllInfo

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFoodVitamins(foodVitamins: List<FoodVitaminCrossRef>)

}