package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.DayHistoryRecords
import com.pinkyuni.fooddiary.entities.History
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(history: History): Long

    @Query("SELECT id FROM HISTORY WHERE record_date = :date AND user_id = :user AND meal_id = :meal")
    fun getHistoryId(date: Long, user: Long, meal: Long): Long

    @Transaction
    fun getOrInsertHistory(history: History): Long {
        val id = insert(history)
        return if (id == -1L) {
            getHistoryId(history.recordDate.time, history.user, history.meal)
        } else {
            id
        }
    }

    @Insert
    fun insertFoodRecord(historyFood: HistoryFoodCrossRef): Single<Long>

}