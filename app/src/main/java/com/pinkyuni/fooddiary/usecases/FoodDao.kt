package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.food.FoodAllInfo
import com.pinkyuni.fooddiary.entities.food.FoodIngredients
import com.pinkyuni.fooddiary.entities.food.FoodUnit
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FoodDao {

    @Insert
    fun insert(food: Food): Single<Long>

    @Update
    fun update(food: Food): Completable

    @Delete
    fun delete(food: Food): Completable

    @Query("SELECT * FROM FOOD WHERE name LIKE  '%' || :name || '%'")
    fun getFood(name: String): Single<List<Food>>

    @Transaction
    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodIngredients(foodId: Long): Single<List<FoodIngredients>>

    @Transaction
    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodInfo(foodId: Long): Single<FoodAllInfo>

    @Query("SELECT * FROM Food")
    fun getFoodList(): Single<List<Food>>

    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodUnits(foodId: Long): Single<FoodUnit>

}