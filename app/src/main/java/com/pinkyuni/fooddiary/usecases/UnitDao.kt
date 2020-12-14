package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Unit
import io.reactivex.Single

@Dao
interface UnitDao {

    @Query("SELECT * FROM Unit")
    fun getUnits(): Single<List<Unit>>

}