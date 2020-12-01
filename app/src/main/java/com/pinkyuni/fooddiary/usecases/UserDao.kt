package com.pinkyuni.usecases

import androidx.room.*
import com.pinkyuni.entities.core.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM Diary_user WHERE name == :name")
    fun getUserByName(name: String): List<User>

    @Query("SELECT * FROM Diary_user")
    fun getUsers(): List<User>
}