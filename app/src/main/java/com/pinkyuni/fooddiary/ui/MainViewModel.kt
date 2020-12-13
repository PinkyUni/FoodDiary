package com.pinkyuni.fooddiary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.entities.MealHistory
import com.pinkyuni.fooddiary.entities.food.toFoodInfo
import com.pinkyuni.fooddiary.utils.DisposeHolder
import com.pinkyuni.fooddiary.utils.SingleLiveEvent
import com.pinkyuni.fooddiary.utils.async

class MainViewModel(private val repository: IRepository, private val disposeHolder: DisposeHolder) :
    ViewModel(), DisposeHolder by disposeHolder {

    val error = SingleLiveEvent<String?>()
    private val _foodInfo = MutableLiveData<FoodInfo>()
    val foodInfo: LiveData<FoodInfo> = _foodInfo

    private val _historyInfo = MutableLiveData<List<MealHistory>>()
    val historyInfo: LiveData<List<MealHistory>> = _historyInfo

    fun getFoodInfo(foodId: Long) {
        repository.getFoodInfo(foodId)
            .async()
            .map { it.toFoodInfo() }
            .subscribe(
                { _foodInfo.value = it },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

    fun getDayHistory(day: Long, user: Long) {
        repository.getHistoryForDay(day, user)
            .async()
            .subscribe(
                { _historyInfo.value = it },
                { error.value = it.message }
            )
            .unsubscribeOnDestroy()
    }

}