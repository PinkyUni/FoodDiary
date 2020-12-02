package com.pinkyuni.fooddiary.ui

import androidx.lifecycle.ViewModel
import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.utils.SingleLiveEvent

class MainViewModel(private val repository: IRepository) : ViewModel() {

    val isError = SingleLiveEvent<String>()

    fun showError(message: String) {
        isError.value = message
    }

    fun getFoodInfo(foodId: Int) = repository.getFoodInfo(foodId)


}