package com.pinkyuni.fooddiary.entities.core

import androidx.room.*
import java.util.*

@Entity(
    tableName = "Diary_user",
    indices = [
        Index("login"),
        Index("gender_id"),
        Index("target_id"),
        Index("activity_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Gender::class,
            parentColumns = ["id"],
            childColumns = ["gender_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Target::class,
            parentColumns = ["id"],
            childColumns = ["target_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Activity::class,
            parentColumns = ["id"],
            childColumns = ["activity_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val login: String,
    val password: String,
    val height: Long,
    val birth: Date,
    @ColumnInfo(name = "target_weight")
    val targetWeight: Float,
    val calories: Long,
    @ColumnInfo(name = "gender_id")
    val gender: Long,
    @ColumnInfo(name = "target_id")
    val target: Long,
    @ColumnInfo(name = "activity_id")
    val activity: Long
)