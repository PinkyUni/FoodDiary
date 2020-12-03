package com.pinkyuni.fooddiary.usecases

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pinkyuni.fooddiary.entities.core.Vitamin

interface VitaminDao {

    @Insert
    fun insert(vitamin: Vitamin): Long

    @Delete
    fun delete(vitamin: Vitamin)

    @Query("SELECT * FROM Vitamin")
    fun getVitamins(): List<Vitamin>

}