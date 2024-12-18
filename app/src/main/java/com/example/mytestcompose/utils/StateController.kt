package com.example.mytestcompose.utils

sealed class StateController<out T> {
    data object Loading : StateController<Nothing>()
    data object Empty : StateController<Nothing>()
    data class Success<out T>(val apiData: T) : StateController<T>()
    data class Failure(val msg: String) : StateController<Nothing>()

}