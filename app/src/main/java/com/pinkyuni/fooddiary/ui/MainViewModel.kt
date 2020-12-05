package com.pinkyuni.fooddiary.ui

import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.data.model.FoodInfo
import com.pinkyuni.fooddiary.entities.food.toFoodInfo
import com.pinkyuni.fooddiary.ui.base.BaseViewModel
import com.pinkyuni.fooddiary.utils.addTo
import com.pinkyuni.fooddiary.utils.async

class MainViewModel(private val repository: IRepository) :
    BaseViewModel<FoodInfo>() {

    fun getFoodInfo(foodId: Long) {
        startLoading()
        repository.getFoodInfo(foodId)
            .async()
            .map { it.toFoodInfo() }
            .subscribe(onSuccessHandler, onErrorHandler)
            .addTo(disposable)
    }

}