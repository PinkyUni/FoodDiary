package com.pinkyuni.fooddiary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.data.model.DayInfo
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.data.model.FoodRecord
import com.pinkyuni.fooddiary.entities.MealHistory
import com.pinkyuni.fooddiary.entities.core.*
import com.pinkyuni.fooddiary.entities.core.Target
import com.pinkyuni.fooddiary.entities.core.Unit
import com.pinkyuni.fooddiary.entities.food.FoodIngredients
import com.pinkyuni.fooddiary.entities.food.FoodUnit
import com.pinkyuni.fooddiary.entities.food.toFoodInfo
import com.pinkyuni.fooddiary.utils.DisposeHolder
import com.pinkyuni.fooddiary.utils.SingleLiveEvent
import com.pinkyuni.fooddiary.utils.async
import com.pinkyuni.fooddiary.utils.getDateMillis

typealias kUnit = kotlin.Unit

class MainViewModel(private val repository: IRepository, private val disposeHolder: DisposeHolder) :
    ViewModel(), DisposeHolder by disposeHolder {

    val error = SingleLiveEvent<String?>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var activities: List<Activity>? = null
    private var genders: List<Gender>? = null
    private var targets: List<Target>? = null
    private var units: List<Unit>? = null
    private var meals: List<Meal>? = null

    fun getFoodInfo(foodId: Long, onComplete: (FoodInfo) -> kUnit) {
        repository.getFoodInfo(foodId)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .map { it.toFoodInfo() }
            .subscribe(
                {
                    _isLoading.value = false
                    onComplete.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getDayHistory(day: Long, onLoaded: (List<MealHistory>) -> kUnit) {
        repository.getHistoryForDay(day)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onLoaded.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getUser(id: Long, onUserLoaded: (User) -> kUnit) {
        repository.getUser(id)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onUserLoaded.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getActivities(onLoaded: (List<Activity>) -> kUnit) {
        if (activities.isNullOrEmpty()) {
            repository.getActivities()
                .async()
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe(
                    {
                        _isLoading.value = false
                        activities = it
                        onLoaded.invoke(it)
                    },
                    { error.value = it.message }
                )
                .unsubscribeOnDestroy()
        } else {
            activities?.let(onLoaded)
        }
    }

    fun getTargets(onLoaded: (List<Target>) -> kUnit) {
        if (targets.isNullOrEmpty()) {
            repository.getTargets()
                .async()
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe(
                    {
                        _isLoading.value = false
                        onLoaded.invoke(it)
                    },
                    { error.value = it.message }
                )
                .unsubscribeOnDestroy()
        } else {
            targets?.let(onLoaded)
        }
    }

    fun getGenders(onLoaded: (List<Gender>) -> kUnit) {
        if (genders.isNullOrEmpty()) {
            repository.getGenders()
                .async()
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe(
                    {
                        _isLoading.value = false
                        onLoaded.invoke(it)
                    },
                    { error.value = it.message }
                )
                .unsubscribeOnDestroy()
        } else {
            genders?.let(onLoaded)
        }
    }

    fun getUnits(onLoaded: (List<Unit>) -> kUnit) {
        if (units.isNullOrEmpty()) {
            repository.getUnits()
                .async()
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe(
                    {
                        _isLoading.value = false
                        onLoaded.invoke(it)
                    },
                    { error.value = it.message }
                )
                .unsubscribeOnDestroy()
        } else {
            units?.let(onLoaded)
        }
    }

    fun getMeals(onLoaded: (List<Meal>) -> kUnit) {
        if (meals.isNullOrEmpty()) {
            repository.getMeals()
                .async()
                .doOnSubscribe { _isLoading.postValue(true) }
                .subscribe(
                    {
                        _isLoading.value = false
                        onLoaded.invoke(it)
                    },
                    { error.value = it.message }
                )
                .unsubscribeOnDestroy()
        } else {
            meals?.let(onLoaded)
        }
    }

    fun addFoodRecord(
        foodId: Long,
        size: Long,
        unitId: Long,
        mealId: Long,
        onComplete: () -> kUnit
    ) {
        val foodRecord = FoodRecord(foodId, size, unitId, mealId)
        repository.addFoodRecord(foodRecord)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onComplete.invoke()
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getFoodList(onLoaded: (List<Food>) -> kUnit) {
        repository.getFoodList()
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onLoaded.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getFoodUnits(foodId: Long, onLoaded: (FoodUnit) -> kUnit) {
        repository.getFoodUnits(foodId)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onLoaded.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getFoodIngredients(foodId: Long, onLoaded: (List<FoodIngredients>) -> kUnit) {
        repository.getFoodIngredients(foodId)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onLoaded.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getTotalCalories(day: Long, onLoaded: (DayInfo) -> kUnit) {
        repository.getDayCalories(day)
            .async()
            .doOnSubscribe { _isLoading.postValue(true) }
            .subscribe(
                {
                    _isLoading.value = false
                    onLoaded.invoke(it)
                },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

}