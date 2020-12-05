package com.pinkyuni.fooddiary.ui.base

import androidx.lifecycle.Observer
import com.pinkyuni.fooddiary.utils.State

interface ResultObserver<T> :  Observer<State<T>> {

    override fun onChanged(state: State<T>?) {
        when (state) {
            is State.Loaded -> onDataLoaded(state.data)
            is State.Complete -> onTaskComplete()
            is State.Error -> onError(state.message)
            is State.Loading -> onTaskLoading()
        }
    }

    fun onTaskLoading() {}
    fun onDataLoaded(data: T)
    fun onTaskComplete()
    fun onError(message: String)

}