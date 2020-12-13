package com.pinkyuni.fooddiary.usecases

import androidx.room.*
import com.pinkyuni.fooddiary.entities.core.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(user: User): Single<Long>

    @Update
    fun update(user: User): Completable

    @Delete
    fun delete(user: User): Completable

    @Query("SELECT * FROM Diary_user WHERE name = :name")
    fun getUserByName(name: String): Single<User>

    @Query("SELECT * FROM Diary_user")
    fun getUsers(): Single<List<User>>

    @Query("SELECT * FROM DIARY_USER WHERE id = :id")
    fun getUserById(id: Long): Single<User>

    @Query("SELECT * FROM Diary_user WHERE login = :login AND password = :password")
    fun getUser(login: String, password: String): Single<User>

}