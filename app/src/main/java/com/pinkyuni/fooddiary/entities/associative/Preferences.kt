package com.pinkyuni.fooddiary.entities.associative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.pinkyuni.fooddiary.entities.core.Category
import com.pinkyuni.fooddiary.entities.core.User

@Entity(
    primaryKeys = ["user_id", "category_id"],
    indices = [
        Index("user_id"),
        Index("category_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Preferences(
    @ColumnInfo(name = "user_id")
    val user: Long,
    @ColumnInfo(name = "category_id")
    val category: Long
)