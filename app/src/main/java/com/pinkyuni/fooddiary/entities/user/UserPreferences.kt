package com.pinkyuni.entities.user

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.entities.associative.Preferences
import com.pinkyuni.entities.core.Category
import com.pinkyuni.entities.core.User

data class UserPreferences(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "category_id",
        associateBy = Junction(Preferences::class)
    )
    val preferences: List<Category>
)