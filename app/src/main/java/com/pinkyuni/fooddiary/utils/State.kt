package com.pinkyuni.fooddiary.utils

sealed class State<out T> {

    data class Loaded<T>(val data: T) : State<T>()

    object Complete : State<Nothing>()

    object Loading : State<Nothing>()

    data class Error(val message: String) : State<Nothing>()
}