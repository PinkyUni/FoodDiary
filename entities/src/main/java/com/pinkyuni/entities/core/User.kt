package com.pinkyuni.entities.core

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    indices = [
        Index("name"), Index("login"),
        Index("gender"), Index("target"), Index("activity")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Gender::class,
            parentColumns = ["id"], childColumns = ["gender"]
        ), ForeignKey(
            entity = Target::class,
            parentColumns = ["id"], childColumns = ["target"]
        ), ForeignKey(
            entity = Activity::class,
            parentColumns = ["id"], childColumns = ["activity"]
        )]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val login: String,
    val password: String,
    val height: Int,
    val birth: Date,
    val targetWeight: Float,
    val calories: Int,
    val gender: Long,
    val target: Long,
    val activity: Long
)