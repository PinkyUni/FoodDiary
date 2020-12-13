package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.Activity
import io.reactivex.Single

@Dao
interface ActivityDao {

    @Query("SELECT * FROM Activity")
    fun getActivities(): Single<List<Activity>>

}