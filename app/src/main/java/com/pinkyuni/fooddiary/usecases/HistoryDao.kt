package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.DayHistoryRecords
import com.pinkyuni.fooddiary.entities.HistoryRecords
import com.pinkyuni.fooddiary.entities.associative.HistoryFoodCrossRef
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HistoryDao {

    @Insert
    fun insert(item: HistoryFoodCrossRef): Single<Long>

    @Update
    fun update(item: HistoryFoodCrossRef): Completable

    @Delete
    fun delete(item: HistoryFoodCrossRef): Completable

    @Query("SELECT * FROM History WHERE record_date = :day AND user_id = :user")
    fun getHistoryForDay(day: Long, user: Long): Single<DayHistoryRecords>

}