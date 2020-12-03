package com.pinkyuni.fooddiary.ui

import androidx.lifecycle.ViewModel
import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.utils.DisposeHolder
import com.pinkyuni.fooddiary.utils.SingleLiveEvent
import com.pinkyuni.fooddiary.utils.async

class MainViewModel(private val repository: IRepository, disposeHolder: DisposeHolder) :
    ViewModel(), DisposeHolder by disposeHolder {

    val isError = SingleLiveEvent<String>()
    val foodInfo = SingleLiveEvent<FoodInfo>()

    fun showError(message: String) {
        isError.value = message
    }

    fun getFoodInfo(foodId: Long) {
        repository.getFoodInfo(foodId)
            .async()
            .doOnError {
                isError.value = it.message
            }
            .doOnSuccess {
                foodInfo.value = it
            }.subscribe()
            .unsubscribeOnDestroy()
    }

}