package com.pinkyuni.entities.user

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.entities.associative.Favourites
import com.pinkyuni.entities.core.Food
import com.pinkyuni.entities.core.User

data class UserFavourites(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "food_id",
        associateBy = Junction(Favourites::class)
    )
    val favourites: List<Food>
)