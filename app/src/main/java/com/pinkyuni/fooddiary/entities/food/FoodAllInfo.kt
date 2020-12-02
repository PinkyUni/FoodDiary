package com.pinkyuni.fooddiary.entities.food

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.entities.associative.FoodIngredientCrossRef
import com.pinkyuni.fooddiary.entities.core.Food
import com.pinkyuni.fooddiary.entities.core.Ingredient
import com.pinkyuni.fooddiary.entities.ingredient.IngredientVitamins

data class FoodAllInfo(
    @Embedded val food: Food,
    @Relation(
        parentColumn = "id",
        entity = Ingredient::class,
        entityColumn = "id",
        associateBy = Junction(
            FoodIngredientCrossRef::class,
            parentColumn = "food_id",
            entityColumn = "ingredient_id"
        )
    )
    val ingredients: List<IngredientVitamins>
)

fun FoodAllInfo.map(): FoodInfo {
    val ingredients = this.ingredients.map { it.ingredient }
    val vitamins = this.ingredients
        .flatMap { it.vitamins }
        .distinct()
        .sortedBy { it.name }
    return FoodInfo(
        food = this.food,
        ingredients = ingredients, vitamins = vitamins
    )
}
