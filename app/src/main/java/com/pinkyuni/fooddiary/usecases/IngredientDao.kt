package com.pinkyuni.fooddiary.usecases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pinkyuni.fooddiary.entities.core.Ingredient
import com.pinkyuni.fooddiary.entities.ingredient.IngredientVitamins

@Dao
interface IngredientDao {

    @Insert
    fun insert(ingredient: Ingredient)

    @Query("SELECT * FROM Ingredient")
    fun getIngredients(): List<Ingredient>

    @Transaction
    @Query("SELECT * FROM Ingredient")
    fun getFoodVitamins(): List<IngredientVitamins>

    @Transaction
    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFoodVitamins(foodId: Long): List<IngredientVitamins>

}