package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Target
import io.reactivex.Single

@Dao
interface TargetDao {

    @Query("SELECT * FROM Target")
    fun getTargets(): Single<List<Target>>

}